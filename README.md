# Hexagonal microservices

#Tematem aplikacji jest system umożliwiający zarządzanie przesyłkami, a jej główne funkcjonalności to:
- Zarządzanie pracownikami (kurierami)
- Zarządzanie usługami (przesyłki kurierskie)
- Kreator generowania tras dla kurierów w oparciu o przypisane przesyłki i ich adresy docelowe
- Kreator generowania tras w oparciu o wybrane punkty na mapie

# Strona techniczna aplikacji:
- Aplikacja została podzielona na 2 elementy: aplikację serwerową składającą się 
z dwóch mikro-serwisów, oraz interfejs użytkownika
- Część serwerową zaimplementowano przy użyciu języka programowania Java wraz 
z biblioteką Spring. Dodatkowymi elementami są warstwa persystencji (PostgreSQL).
- Część związaną z interfejsem użytkownika wykonano przy użyciu biblioteki React.js.
- Aplikacja jest wdrożona w środowisku kontenerów: Docker.

# Uruchomienie aplikacji
1. Zbuduj pliki .jar aplikacji - mvc clean install
2. Uruchom komendę docker-compose up --build -d
3. Aplikacja będzie dostępna pod adresami:
- localhost:80/courier
- localhost:80/route
