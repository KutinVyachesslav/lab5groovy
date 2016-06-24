import groovy.json.JsonSlurper

def urlString = "http://localhost:9000/oauth/token"

def auth = "clientapp:123456".bytes.encodeBase64().toString();
println auth

def url = new URL(urlString)
def connection = url.openConnection()
connection.setRequestMethod("POST")
connection.doOutput = true
connection.setRequestProperty("Authorization","Basic "+auth);

def writer = new OutputStreamWriter(connection.outputStream)
writer.write("password=spring&username=roy&grant_type=password&scope=read%20write&client_secret=123456&client_id=clientapp")
writer.flush()
writer.close()
connection.connect()
def responseText = connection.content.text

println responseText


def jsonSlurper = new JsonSlurper()
def object = jsonSlurper.parseText(responseText)

assert object instanceof Map
def access_token = object.access_token
println "access_token=${access_token}"

url = new URL("http://localhost:9000/users")
connection = url.openConnection()
connection.setRequestProperty("Authorization","Bearer "+access_token);
connection.connect()
responseText = connection.content.text

println responseText


print "END"

