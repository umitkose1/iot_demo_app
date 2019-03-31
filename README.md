# iot_demo_app
Android IoT app with using CloudMqtt Server
- This app provide you to receive data from mqttCloud server.
- If you run this Phyton code on your computer, it behaves like a device which sends data to mqtt server.
- Second step Mqtt server collects these datas.
- Third step Mqtt server publish your datas to mobile devices.

# Python Script Code
```
import json
import paho.mqtt.client as mqtt
import random
import time
import threading
import sys
import xlrd
import time

mqttc = mqtt.Client("client1", clean_session=False)
mqttc.username_pw_set("wtuaxeda", "UkFVlYUdK-3w")
mqttc.connect("m16.cloudmqtt.com", 16188, 60)
file_location = "C:\Users\Admin\Desktop\Python/test_data.xlsx"   // Download excel folder at attachment and change directory with your.
workbook = xlrd.open_workbook(file_location)
sheet = workbook.sheet_by_index(0)
sheet.cell_value(0,0)
for col in range(sheet.ncols):
    time.sleep(2)
    print(sheet.cell_value(0,col))
    print(sheet.cell_value(1,col))
    print(sheet.cell_value(2,col))
    print(sheet.cell_value(3,col))
    mqttc.publish("sensor/temp", payload=sheet.cell_value(0,col), qos=0)
    mqttc.publish("sensor/text1", payload=sheet.cell_value(1,col), qos=0)
    mqttc.publish("sensor/text2", payload=sheet.cell_value(2,col), qos=0)
    mqttc.publish("sensor/text3", payload=sheet.cell_value(3,col), qos=0)
    if col == 11:
        mqttc.publish("sensor/olcum", payload="OK", qos=0)
```
### Download this Excel file and change directory folder with your. 
https://github.com/umitkose1/iot_demo_app/issues/1#issue-427453693
