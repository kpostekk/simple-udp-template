Zadanie polega na komunikacji z użyciem protokołu TCP/UDP. Protokół komunikacji jest tekstowy. W przypadku protokołu TCP odebranie pojedyńczej odpowiedzi polega na wczytaniu jedej linii tekstu; analogicznie, wysłanie pojedyńczej odpowiedzi polega na wysłaniu jednej linii tekstu. Natomiast w przybadku protokołu UDP odebranie pojedyńczej odpowiedzi polega na wczytaniu pojedyńczego datagramu z bajtami reprezentującymi jedną linię tekstu; analogicznie, wysłanie pojedyńczej odpowiedzi polega na wysłaniu jednego datagramu z bajtami reprezentującymi jedną linię tekstu. W szczególności wszystkie liczby powinny najpierw zamieniane na tekstową reprezentację. Twoją początkową flagą jest liczba 118470. Flagę tę należy wysłać do serwera TCP aby zainicjować komunikację.


Serwer TCP działa na adresie 172.21.48.197 i porcie 16777.


1. Utwórz gniazdo UDP. Wyślij do serwera TCP jedną linię w formacie adres:port, gdzie adres jest adresem IP Twojego gniazda UDP, a port jego numerem portu. Wykonaj poniższe polecenia używając protokołu UDP i gniazda, które właśnie utworzyłeś.

2. (3. prepatch) otrzymaj paket identyfikujący od serwera.

3. Wyślij numer portu z którego się komunikujesz.

4. Odbierz liczbę naturalną x. Oblicz największą liczbę naturalną k, taką, że k podniesione do potęgi 2 jest nie większe niż wartość x. Odeślij wartość k.

5. W 4 kolejnych liniach odbierz 4 liczb(y) naturalne(ych). Policz ich największy wspólny dzielnik i wynik odeślij.

6. Odbierz napis. Usuń z niego wszystkie wystąpienia 0 i odeślij wynik.


Odbierz finalną flagę i wpisz ją poniżej.