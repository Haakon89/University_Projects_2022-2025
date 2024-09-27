# Rapport – innlevering 3
**Team:** Team Undefined - Alf Bjarne Klæhaug, Eivind Bjørnbakken Eide, Håkon Sørensen, Oscar Gussiås

## Prosjektrapport

- Vi føler rollene fortatt fungerer bra. 
- Hva rollene faktisk innebærer:
  - Alf: Har videreutviklet Toolbar som ansvarlig for GameUI. Som ansvarlig for tester har det ikke blitt gjort så mye denne gangen, men det ligger bra an. 
  - Eivind: Som lydansvarlig: Begynt med å lage lyd. Synes det har gått bra. Har vært ute og tatt opp lyder som har blitt brukt i spillet. Synes det har gått greit som lead software developer. Gjør i blant en del store endringer, og håper ikke det har vært for mye i veien for de andre. Ikke så mye å gjøre med Maven per nå.
  - Håkon: Prosjektorganisator: For det meste gått fint av seg selv. Litt jobb med innkalling til møter osv. Ikke gjort så mye på animasjon siden sist gang. Karakterdesign også tilnærmet seg ferdig. Jobbet mest med SpriteGenerator-klassen for å finne de riktige spritesene til de riktige elementene. Har også jobbet med å få på plass sesonger og tidssystemer.
  - Oscar: Har fikset på hovedmeny, pausemeny og meny for instruksjoner som GUIDesegner. Har også opprettet en Shop-meny som må jobbes mer med. Maven går stort av seg selv. Som ansvarlig for dokumentasjon, så dokumenterer jeg akkurat nå:D
- Gruppedynamikken er bra! Ingen uenigheter. Kommer godt overenens!
- Kommunikasjonen fungerer også bra. Alle er tilgjengelige for kommunikasjon gjennom Discord. Møtes minst en gang i uken. 
- Retrospektiv: Spillet ligger veldig bra an slik vi ser det, noe som er et symptom på at samarbeidet og prosjektstrukturen fungerer bra. Vi har lite merge conflicts, og bra med test coverage. Kan bli flinkere til å jobbe med varierte fremgangsmpter. Alle er ellers tilfedse med sine respektive arbeidsoppgaver, og vi er fornøyd med måten vi har delt opp prosjektet. Dette er mye grunnet at vi har vært godt organiserte, og hatt en plan fra starten av.
- Ikke så stor forskjell på bidrag. Forskjellen skyldes til en viss grad hvor ofte man committer når man jobber. Oscar og Alf sine commits er også fortsatt delt utover flere antall brukere, uten at vi helt vet hvorfor.
- Bli flinkere til å bruke test-driven development
- Bli flinkere til å bruke parprogrammering

## Krav og spesifikasjon

- Vi har kommet forbi MVP, og jobber nå med å videreutvikle spillet.
- Spillet er fortsatt ganske minimalt, så vi prioriterer å legge til ny
  funksjonalitet fremfor å utdype det som allerede er her.
- Siden forrige oblig har vi fullført kravene om å ha en pausemeny, et tidsystem
  og et kamera som følger spilleren.  Kravene om å ha planter som gror og et
  inventorysystem jobber vi fortsatt med.

### Spesifikke krav

- Man må kunne plante noe, når man har vannet det over flere dager så vil planten gro helt til den er ferdig grod.
  - Brukerhistorier: Som en spiller ønsker jeg 
  - Arbeidsoppgaver:
    - Vi må legge til funksjonalitet i Tile klassen sånn at den kan ta imot et som et frø eller plante.
    - Vi må ha en måte å telle dager og sjekke om planten er blitt vannet.

- Ha menyer hvor vi kan styre inventory.
  - Brukerhistorie: Jeg ønsker å kunne ha flere items i inventoriet som jeg kan legge ut på toolbaren for å kunne bruke den.
  - Akseptanskriterier:
    - Må ha en måte å åpne inventory og kontroller som kan bytte ut det som ligger på toolbaren med det som ligger i inventory.
  Arbeidsoppgaver:
    - Vi må lage en klasse for inventory som jobber sammen med toolbar klassen for å bestemme hva som ligger i inventory og hva som er i toolbaren.
    - Vi må også bygge en kontroller for inventory som bruker mouse pickup-drag-release for å plukke opp items og flytte dem til og fra toolbaren. 

- Selge og kjøpe items
  - Brukerhistorie: Som en spiller ønsker jeg å kunne selge det jeg høster og kjøpe items som kan hjelpe meg slik at 
  - Akseptansekriterier:
    - Det må være et system som holder styr på hvor mye penger man har
    - Man må kunne kjøpe items hvis man har nok penger
    - Man må kunne selge items man har for å motta penger
  - Arbeidsoppgaver
    - Vi må lage en Screen hvor man kan kjøpe eller selge
    - Player-klassen må holde styr på hvor mye penger den har
    - Det må være synlig hvor mye penger spilleren har

- Forenkle klassehierarkiet i modellen
  - Brukerhistorie: Som utvikler ønsker jeg et enkelt klassehierarki innen modellen slik at det er lett å utvide spillet
  - Akseptansekriterier:
    - Det må være mulig å lage nye tiles, entities og items ved å bare lage en ny klasse
  - Arbeidsoppgaver:
    - Lage et system som finner alle underklasser av Tile, Entity og Item
    - Definere visningen ut i fra de klassene

## Produkt og kode

### Klassediagram

<img src="oblig3/klassediagram.svg">
