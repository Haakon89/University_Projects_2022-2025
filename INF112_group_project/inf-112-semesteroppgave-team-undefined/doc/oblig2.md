# Rapport – innlevering 2
**Team:** Team Undefined – Alf Bjarne Klæhaug, Eivind Bjørnbakken Eide, Håkon Sørensen, Oscar Gussiås

## Produkt og kode

### Klassediagram

<img src="oblig2/klassediagram.svg" />

## Prosjektrapport

- Vi føler at rollene vi har delt oss inn i har fungert bra så langt.
- Alle oppgavene som har dukket har vi kunnet formidle til en allerede eksisterende rolle.
- Vi har gått litt vekk fra test driven development nå som vi er kommet litt lenger inn i prosjektet og møter på klasser og strukturer vi ikke har så mye erfaring med. Da blir det heller at vi prøver ut ting til det fungerer og så ser vi på tester etterpå.
- Vi har blitt mer kjent med Jacoco for å sjekke test coverage og vi har så vidt fått introdusert SpotBugs til prosjektet
- Hadde litt diskusjoner om hvordan grafikk skulle løses, men kom frem til et system som fungerer bra uten stor uenighet. Har vært litt diskusjoner om navngivning på metoder og klasser, men har ikke vært noen uløselige uenigheter. Gruppen jobber bra sammen.
- Vi kommuniserer bra gjennom discord og har ukentlige møter hvor vi disskuterer hva som må bli gjort fremmover og hva vi er ferdig med.
- Vi har nesten klart å nå målene vi satt for MVP, det vi mangler er et kamera som følger spilleren. 

### Retrospektiv: 

Vi føler at prosjektstrukturen vår har fungert og vi har funnet en måte å jobbe på som ikke skaper store konflikter verken i kode eller i sammarbeid. Vi hadde en del mergekonflikter i starten, men vi har blitt flinkere å kommunisere om hva vi jobber med og hvor vi jobber sånn at vi slipper problemer senere.

### Kodebidrag:
På analytics så ser vi det er litt spredning i commits. Noe av det er på grunn av hver persons commit strategi og hvor mye arbeid som blir lagt inn før hver commit. Enkelte gjør ofte commits etter hver fullførte oppgave, mens andre gjør ferdig flere oppgaver før en commit. Analytics deler også personer opp i flere av en eller annen grunn. For eksempel Oscar sine commits har blitt fordelt på fem forskjellige brukere og Alf har blitt fordelt på to forskjellige.

### Hva kan vi bli bedre på til neste gang:

- Bli bedre på å bruke issuebordet.
- Bli bedre på å teste først så lage det testene trenger.

## Krav og Spesifikasjoner:

Vi er ikke kommet forbi MVP så vi har ikke nådd alle kravene vi satt forrige gang, men vi er veldig nært til å gjøre de kravene ferdig. 

### Krav vi prioriterer:
- Ha planter som gror.
  - Brukerhistorie: Jeg ønsker å kunne gro planter i spillet.

- Ha en pausemeny:
  - Brukerhistorie: Jeg ønsker å ha en meny hvor jeg kan sette spillet på pause (som faktisk fungerer), hvor jeg også kan se på hva jeg har i inventory.
  - Akseptanskriterier: Man må kunne plante noe, når man har vannet det over flere dager så vil planten gro helt til den er ferdig grod.  - Arbeidsoppgaver:
      - Vi må legge til funksjonalitet i Tile klassen sånn at den kan ta imot et som et frø eller plante.
      - Vi må ha en måte å telle dager og sjekke om planten er blitt vannet.

- Ha menyer hvor vi kan styre inventory.
    - Brukerhistorie: Jeg ønsker å kunne ha flere items i inventoriet som jeg kan legge ut på toolbaren for å kunne bruke den.
    - Akseptanskriterier:
      - Må ha en måte å åpne inventory og kontroller som kan bytte ut det som ligger på toolbaren med det som ligger i inventory.
    Arbeidsoppgaver:
      - Vi må lage en klasse for inventory som jobber sammen med toolbar klassen for å bestemme hva som ligger i inventory og hva som er i toolbaren.
      - Vi må også bygge en kontroller for inventory som bruker mouse pickup-drag-release for å plukke opp items og flytte dem til og fra toolbaren. 
- Legge til årstider og telle dager.
  - Brukerhistorie: Jeg ønsker at spillet skal gå over flere dager og årstider.
  - Akseptanskriterier:
    - Spillet må kunne se hvor mange dager som er gått og endre årstider når det er gått X antall dager.
  - Arbeidsoppgaver:
    - Vi må bygge et system som teller dager som er gått og bruke enumen seasons til å sette den riktige sesongen utifra hvor mange dager som er gått.
- Gjøre ferdig krav om kamera som følger spiller og verden som er større en det man ser på skjermen.
  - se [forrige oblig](oblig1.md) for utdypning av dette kravet.

Vi prioriterer basis funksjoner som må til for at spillet skal kunne gjøre det det er laget for, sånn som bevegelse, planting og ting som gror.