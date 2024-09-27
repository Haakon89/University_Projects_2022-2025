# Gruppe 5 - Team Undefined - Sun Mist Mountain

## Team

- Alf Bjarne Klæhaug
- Eivind Bjørnbakken Eide
- Håkon Sørensen
- Oscar Gussiås

## Game Concept

Farming/life sim eks.(Stardew Valley, Animal Crossing). Kunne ha en bevegelig karakter. Karakteren skal ha en helse og stamina måler. Skal ha muligheten til å gjøre klar jord, plante planter, vanne, høste inn og selge. Kunne kjøpe frø, dyr?, klær?, mat?. Skal kunne bygge hus og få nye beboere i byen?

## Controls:

- W - beveg opppover
- A - beveg mot venstre
- S - beveg nedover
- D - beveg mot høyre
- E - åpne meny
- R - butikk
- I - åpne inventory
- TAB - endre aktiv toolbar
- Flytt på musepekeren for å velge tiles
- Venstre museklikk - bruk gjenstand
- Scrolle opp og ned/1-9 og 0 - velg gjenstand i toolbar 

## Running the program

- Run `mvn compile exec:java`
- Run `mvn spotbugs:spotbugs spotbugs:gui` to run SpotBugs on the project
- Run `mvn test jacoco:report` to generate a code coverage report.  The results
  can be seen by opening `target/site/jacoco/index.html` in a browser.

## Known bugs

- muse scrolling endrer det aktive itemet raskt

## Asset sources

"Metal UI" skin. Created by Raymond "Raelus" Buckley, 2016. Licence CC BY 4.0.
Available through: https://github.com/czyzby/gdx-skins/tree/master/metal

See [the license file](doc/annen_dokumentasjon/lisens.md) for more information.
