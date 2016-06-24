/**
 * Created by Student on 24.06.2016.
 */
def user = "clientapp:123456"
def auth = "Basic " + user.bytes.encodeBase64().toString()
def oaurl = "http://localhost:8080/oauth/token"
def client_id = "clientapp"
def client_secret = "123456"
def scope = "read%20write"
def grant_type = "password"
def username = "roy"
def password = "spring"
def body = "password=${password}&username=${username}&grant_type=${grant_type}&scope=${scope}&client_secret=${client_secret}&client_id=${client_id}"
print body
print auth
