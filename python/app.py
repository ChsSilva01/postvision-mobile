from flask import Flask, request, jsonify
import mediapipe as mp
import cv2
import base64
import numpy as np

app = Flask(__name__)
mp_pose = mp.solutions.pose

@app.route("/process", methods=["POST"])
def process_frame():
    data = request.get_json()
    image_data = base64.b64decode(data["image"])
    np_arr = np.frombuffer(image_data, np.uint8)
    img = cv2.imdecode(np_arr, cv2.IMREAD_COLOR)

    landmarks = []
    with mp_pose.Pose(static_image_mode=False) as pose:
        results = pose.process(cv2.cvtColor(img, cv2.COLOR_BGR2RGB))
        if results.pose_landmarks:
            for lm in results.pose_landmarks.landmark:
                landmarks.append({"x": lm.x, "y": lm.y, "z": lm.z})

    return jsonify({"landmarks": landmarks})
