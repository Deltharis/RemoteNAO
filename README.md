RemoteNAO
=========
RemoteNAO to zdalne centrum komend głosowych dla robota NAO. Projekt ten pozwala na tworzenie zdalnych implementacji funkcjonalności dla robota NAO dostępnych z poziomu RESTowego API i możliwych do podmienienia w locie bez potrzeby dostępu do samego robota.

1. Budowanie projektu
---------------------

Projekt składa się z dwóch części. Pierwszą jest mavenowy projekt Javy 1.7 przeznaczony do deployowania na serwer Apache Tomcat 7.0

Drugim zaś, są dwa pliki .py odpowiadające za obsługę robota. Należy, po skonfigurowaniu IP i portu robota w kodzie client.py odpalić go w środowisku pythona majacym dostęp do biblioteki naoqi.

2. Symulowanie robota
---------------------

Możliwe jest uruchomienie aplikacji na wirtualnym robocie dostępnym w ramach pakietu Choreographe. Nie dysponuje on modułem rozpoznawania tekstu, przez co niezbędna jest zmiana w kodzie sprawiająca że korzystamy z wersji Dummy (przyjmującej input konsolowy) tego modułu dostępnego w ramach pliku speechReco.py. 

3. Dokumentacja
---------------

Dokumentacja na wiki projektu
