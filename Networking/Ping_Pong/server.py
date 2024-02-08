from sanic import Sanic
from sanic.response import json
from datetime import datetime

# server name.
app = Sanic("ping-pong-server")

@app.route('/ping-endpoint')
async def ping_endpoint(request):
    
    # return formatted json object with time.
    current_time = datetime.now().strftime("%H:%M:%S")
    return json({'pong': current_time})

if __name__ == '__main__':
    app.run(host='localhost', port=8000)
