from fastapi import FastAPI
import pickle

app = FastAPI()

model = pickle.load(open("model.pkl", "rb"))
vectorizer = pickle.load(open("vectorizer.pkl", "rb"))

@app.post("/predict")
def predict(payload: dict):
    text = payload["text"]
    X = vectorizer.transform([text])
    pred = model.predict(X)[0]
    return {"category": pred}