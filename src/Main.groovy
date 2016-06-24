//@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.5.0-RC2' )
//
//import groovyx.net.http.HTTPBuilder
//import groovyx.net.http.ContentType
//import groovyx.net.http.Method
//import groovyx.net.http.RESTClient


//curl -X POST -k -vu clientapp:123456 http://localhost:9000/oauth/token -H "Accept: application/json" -d "password=spring&username=roy&grant_type=password&scope=read write&client_secret=123456&client_id=clientapp"
def urlString = "http://localhost:9000/oauth/token"


//def queryString = "privatekey=${config.recaptcha.privateKey}&remoteip=${remoteIp}&challenge=${challenge}&response=${URLEncoder.encode(response)}"
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
def recaptchaResponse = connection.content.text

print recaptchaResponse
//log.debug(recaptchaResponse)


// Submit a request via GET
//def response = ApiConsumer.postText(url, path, query)

// Submit a request via POST
//def response = ApiConsumer.postText(url, path, query)
print "aaaaa"


/*
class ApiConsumer {

    static def postText(String baseUrl, String path, query, method = Method.POST) {
        try {
            def ret = null
            def http = new HTTPBuilder(baseUrl)

            // perform a POST request, expecting TEXT response
            http.request(method, ContentType.TEXT) {
                uri.path = path
                uri.query = query
                headers.'User-Agent' = 'Mozilla/5.0 Ubuntu/8.10 Firefox/3.0.4'
                headers.'Accept' = 'Accept: application/json'

                // response handler for a success response code
                response.success = { resp, reader ->
                    println "response status: ${resp.statusLine}"
                    println 'Headers: -----------'
                    resp.headers.each { h ->
                        println " ${h.name} : ${h.value}"
                    }

                    ret = reader.getText()

                    println 'Response data: -----'
                    println ret
                    println '--------------------'
                }
            }
            return ret

        } catch (groovyx.net.http.HttpResponseException ex) {
            ex.printStackTrace()
            return null
        } catch (java.net.ConnectException ex) {
            ex.printStackTrace()
            return null
        }
    }

    static def getText(String baseUrl, String path, query) {
        return postText(baseUrl, path, query, Method.GET)
    }

}
*/
