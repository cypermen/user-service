import requests

api = 'http://localhost:8081/users'

headers ={
    'Connection': 'keep-alive',
    'Cache-Control': 'max-age=0',
    'Upgrade-Insecure-Requests': '1',
    'User-Agent': 'Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36 OPR/40.0.2308.81',
    'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
    'DNT': '1',
    'Accept-Encoding': 'gzip, deflate, lzma, sdch',
    'Accept-Language': 'ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4'
}
def test_user_empty_get():
    id = '7bd047cb-a57e-412c-9d83-81c50e3e3902'
    response = requests.get(f'{api}/{id}', headers=headers)
    assert response.status_code == 200


def test_user_save_and_get():
    parameters = "?name=Benjamin&departmentId=7bd047cb-a57e-412c-9d83-81c50e3e3902"
    response = requests.post(f'{api}/{parameters}',headers=headers)
    assert response.status_code == 200
    assert response.json().get('name') == 'Benjamin'
    createdUserId = response.json().get('id')
    responseUser = requests.get(f'{api}/{createdUserId}',headers=headers)
    assert responseUser.status_code == 200
    assert responseUser.json().get('name') == 'Benjamin'
