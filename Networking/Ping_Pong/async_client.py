import asyncio
import json
import aiohttp
import time

endpoint = 'http://localhost:8000/ping-endpoint'

# time between pings.
ping_delay = 60

# time until termination.
timeout_delay = 120

async def ping_server():
    async with aiohttp.ClientSession() as sessh:

        # Initialize last res time.
        last_res_time = time.time()

        while True:
            try:
                async with sessh.get(endpoint, timeout=5) as res:

                    # get and print res.
                    data = await res.text()
                    pong = json.loads(data)
                    print(f'pong_res: {pong}')

                    # Update last res time.
                    last_res_time = time.time()

            # no res from server
            except aiohttp.ClientError as e:
                elapsed_time = time.time() - last_res_time

                # print time since last res.
                print("no res in " + str(elapsed_time)[0:4] + " seconds")

                # print error.
                print(e)

            await asyncio.sleep(ping_delay)
            elapsed_time = time.time() - last_res_time
            if elapsed_time >= timeout_delay:
                print("terminating... (no res in: " +
                      str(elapsed_time)[0:4] + " seconds)")
                break

loop = asyncio.get_event_loop()
loop.run_until_complete(ping_server())
