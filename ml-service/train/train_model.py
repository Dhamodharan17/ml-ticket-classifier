import pandas as pd
import pickle
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.linear_model import LogisticRegression

df = pd.read_csv("tickets.csv")

X = df["text"]
y = df["label"]

# Term Frequency-Inverse Document Frequency Vectorizer
vectorizer = TfidfVectorizer()
X_vec = vectorizer.fit_transform(X)

# classification algorithm that predicts probabilities
model = LogisticRegression()
model.fit(X_vec, y)

# Python pickle file used for object serialization
pickle.dump(model, open("../app/model.pkl", "wb"))
pickle.dump(vectorizer, open("../app/vectorizer.pkl", "wb"))

print("Model trained and saved")

#python train_model.py - train to get pkl files