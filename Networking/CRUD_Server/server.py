from sanic import Sanic
from sqlalchemy import select
from sqlalchemy.ext.asyncio import create_async_engine, AsyncSession
from sqlalchemy.orm import declarative_base, sessionmaker, selectinload, relationship
from sqlalchemy import Integer, Column, ForeignKey, String
from sanic.response import json
from sqlalchemy.orm import sessionmaker, scoped_session

app = Sanic("my_app")
Base = declarative_base()

class BaseModel(Base):
    __abstract__ = True
    id = Column(Integer(), primary_key=True)

class Person(BaseModel):
    __tablename__ = "person"
    name = Column(String())
    cars = relationship("Car")

    def to_dict(self):
        return {"name": self.name, "cars": [{"brand": car.brand} for car in self.cars]}

class Car(BaseModel):
    __tablename__ = "car"
    brand = Column(String())
    user_id = Column(ForeignKey("person.id"))
    user = relationship("Person", back_populates="cars")

# Create a new database file
database_file = "test.db"
bind = create_async_engine(f"sqlite+aiosqlite:///{database_file}", echo=True)
Session = sessionmaker(bind, class_=AsyncSession, expire_on_commit=False)

@app.middleware("request")
async def inject_session(request):
    session = scoped_session(Session)()
    request.ctx.session = session
    request.ctx.session_ctx_token = session

from contextvars import ContextVar

session_ctx_var = ContextVar("session")

@app.middleware("request")
async def inject_session(request):
    request.ctx.session = Session()
    session_ctx_var.set(request.ctx.session)

@app.middleware("response")
async def close_session(request, response):
    session = session_ctx_var.get()
    if session is not None:
        await session.close()
    return response
    
@app.post("/user")
async def create_user(request):
    session = request.ctx.session
    async with session.begin():
        car = Car(brand="Tesla")
        person = Person(name="foo", cars=[car])
        session.add_all([person])
    return json(person.to_dict())

@app.get("/user/<pk:int>")
async def get_user(request, pk):
    session = request.ctx.session
    async with session.begin():
        stmt = select(Person).where(Person.id == pk).options(selectinload(Person.cars))
        result = await session.execute(stmt)
        person = result.scalar()

    if not person:
        return json({})

    return json(person.to_dict())

if __name__ == "__main__":
    # Create tables
    Base.metadata.create_all(bind)

    app.run(host="0.0.0.0", port=8000)