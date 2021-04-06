# ZeroIP

## Description
ZeroIP is an Open Source Dynamic DNS service. It uses a domain name from a PaaS such as heroku (example.herokuapp.com)  instead of a dymanic IP address or URL to access your computer remotely. The server can be run in 2 modes. 

- updater mode (client mode) - The server is run on the remote computer and periodically updates the computers public IP address onto a mongodb database which it can then be read by the application in redirection mode.

- redirection mode - This makes it so that when your domain name (from a PaaS such as heroku i.e example.herokuapp.com) is accessed iy yhrn redirects the request to your remote computer by reading the latest updated IP from a mongodb database.


## Pre-Requisits
- setup a mongodb cluster on your favourite NoSQL provider
- setup an application environment from your favourite PaaS service, ensure that you use one that provides a public domain .


## Usage
- setup your configuration for your NoSQL instance in the applications.properties file (this example uses mongodb)
`spring.data.mongodb.uri = <mongodb uri>`

### Updater Mode (Client)
- set the runmode to updater in the applications.properties
- `run.mode = updater`

### Redirection Mode 
- set the runmode to redirectioner in the applications.properties
- `run.mode = redirection`

### URL Structure
Due to the nature of the application and the nature of browser symbol interpretation, the application follows the following convention to access applications on different ports

`https://example.herokuapp.example/8080` which translates to `http://<ip>:8080` (i.e http://34.56.78.90:8080)
the first path parameter always refers to a port, leaving it empty will always default to a port 80

## Problem Area
I developed this application because unlike most internet providers in first world countries, (some) mozambican internet providers (yes i am mozambican) charge a hefty fee for a static ip. So rather then opting for such i decided to create a little programe that does the same updates my public ip and maps it to a domain name. 

## Notes
 - the application can be used with other Databases 
 - the redirection functionality forces the browser to update the url from your specified domain name to your public ip, making it visible in broswer. Consider using forward rather than redirect if this does not meet your needs.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as where applicable.

## License
[MIT](https://choosealicense.com/licenses/mit/)
