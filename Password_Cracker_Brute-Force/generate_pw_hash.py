from concurrent.futures.thread import _worker
import threading
import time
import hashlib;

password_hash = "a74277500228f7b4cfa8694098443fc5"

password_test = 'bajs22'.encode() # b'<PW>'

generated_hash = hashlib.md5(password_test).hexdigest()

print(generated_hash)



