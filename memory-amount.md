# info
```sh
curl -X GET http://localhost:7051/api/memory-amount/info 
```
# increase
```sh
curl -X POST http://localhost:7051/api/memory-amount/fulfill?amount=1
```
# exceed
```sh
curl -X POST http://localhost:7051/api/memory-amount/exceed
```
# clear
```sh
curl -X DELETE http://localhost:7051/api/memory-amount 
```