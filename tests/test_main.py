import requests
import socket

hostname = socket.gethostname()

ip_address = socket.gethostbyname(hostname)

api = f'http://{ip_address}:8081/users'

def test_health():
    response = requests.get(f'{api}/health', verify=False)
    assert response.status_code == 200

def test_user_empty_get():
    id = '7bd047cb-a57e-412c-9d83-81c50e3e3902'
    response = requests.get(f'{api}/{id}', verify=False)
    assert response.status_code == 200
    assert len(response.json()) == 0

def test_user_save_and_get():
    parameters = "?name=Benjamin&departmentId=7bd047cb-a57e-412c-9d83-81c50e3e3902"
    response = requests.post(f'{api}/{parameters}', verify=False)
    assert response.status_code == 200
    assert response.json().get('name') == 'Benjamin'
    createdUserId = response.json().get('id')
    responseUser = requests.get(f'{api}/{createdUserId}', verify=False)
    assert responseUser.status_code == 200
    assert responseUser.json().get('name') == 'Benjamin'