import requests

api = 'http://localhost:8081/book'



def test_user_empty_get():
    s = requests.Session()
    id = '7bd047cb-a57e-412c-9d83-81c50e3e3902'
    response = s.get(f'{api}/find/{id}')
    print(response.status_code)
    assert response.status_code == 200


def test_user_save_and_get():
    s = requests.Session()
    parameters = "?title=cypermen&authorId=7bd047cb-a57e-412c-9d83-81c50e3e3902"
    response = s.post(f'{api}/add/{parameters}')
    print(response.status_code)
    assert response.status_code == 200
    assert response.json().get('title') == 'cypermen'
    createdBookId = response.json().get('id')
    responseBook = s.get(f'{api}/find/{createdBookId}')
    assert responseBook.status_code == 200
    assert responseBook.json().get('title') == 'cypermen'
