# wmts-to-tms-proxy
*WMTS to TMS proxy*

The frontend of this proxy behaves like a TMS. The back-end talks to a WMTS. Use this proxy to talk to a WMTS like if it were a TMS.

# Installatie
Bouw de docker image met `./build-docker-image.sh [tag]`

Deploy de applicatie via de compose map in deze repo. Vergeet niet de juiste versie te zetten in de deploy scripts.

# Laag URL aanpassen
In de map `config/tileMapResourceFiles` staan alle configuratie bestanden voor de verschillende lagen.
In het *.properties bestand staan een property `baseUrl` en `layerName`. Pas deze naar behoefte aan.

# Laag toevoegen aan de wmts-to-tms-proxy
Maak in de map `config/tileMapResourceFiles` de capabilities xml en properties bestand aan voor de nieuw toe te voegen
laag. De bestandsnaam wordt gebruikt in de URL van de wmts-to-tms-proxy. Voeg de config voor de nieuwe laag vervolgens
nog toe in het algemene capabilities document in `config/Capabilities/Capabilities.xml`.
