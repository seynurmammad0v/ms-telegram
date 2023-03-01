# ms-telegram

## For local run
### --ngrok
docker run -it -e NGROK_AUTHTOKEN=<token> ngrok/ngrok:latest http host.docker.internal:8081
### --redis
docker run -d --name redis-stack-server -p 6379:6379 redis/redis-stack-server:latest
### --postgres 
docker run --name ms-telegram -p 5432:5432 -e POSTGRES_PASSWORD=pass -d postgres


