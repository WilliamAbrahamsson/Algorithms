
- Compile: javac Server.java
- Run: java Server 8000 public

Test 302 Redirect:
- Enter /a/b.html and server will respond with status code 302 and redirect you to /a/b/b.html.

Test 500 Generic error response:
- Uncomment the try/catch segment in handleRequest(). Recompile and run the server, then make a GET request. 

Test 404 Not found:
- Access a page that dosen't exist, for example /a/c.html. The server will respond with status code 404.