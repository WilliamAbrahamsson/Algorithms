import asyncio
import random
import math

async def calculate_pi(n):
    x, y, m = 0.0, 0.0, 0.0

    for i in range(n):
        x = random.random()
        y = random.random()

        if math.pow(x, 2) + math.pow(y, 2) <= 1.0:
            m += 1.0

    return 4 * m / n


async def main():
    tasks = []
    num_simulations = 10

    for i in range(num_simulations):
        task = asyncio.create_task(calculate_pi(1000000))
        tasks.append(task)

    results = await asyncio.gather(*tasks)
    print(results)

asyncio.run(main())
