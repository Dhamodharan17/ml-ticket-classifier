FROM python:3.10-slim

WORKDIR /app
COPY requirements.txt .
RUN pip install -r requirements.txt

COPY app/ .

CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8000"]

# cd "c:\Users\DSUNDAR\Desktop\the dsundar\dsundar git projects\ml-ticket-classifier\ml-service" ; 
# docker build -t ml-ticket-classifier -f train/Dockerfile .

# docker run -d -p 8000:8000 --name ml-ticket-classifier-container ml-ticket-classifier
# http://localhost:8000/docs