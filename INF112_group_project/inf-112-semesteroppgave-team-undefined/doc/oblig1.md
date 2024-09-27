# Rapport – innlevering 1
**Team: Team Undefined – Alf Bjarne Klæhaug, Eivind Bjørnbakken Eide, Håkon Sørensen, Oscar Gussiås

Referater til møtene holdt til første innlevering ligger i mappen
[/doc/Møte_referat](/doc/Møte_referat/):

- [28. januar](/doc/Møte_referat/møtereferat_28_01_24.md)
- [7. februar](/doc/Møte_referat/møtereferat_07_02_24.md)
- [12. februar](/doc/Møte_referat/møtereferat_12_02_24.md)

## A0:
- Gruppenavn: Team Undefined.
- Discord gruppe er laget.
- Team Undefined, kompetansekartlegging:
    Alf: INF100, INF101, INF102, for det meste lagd programmer med tiles/grids

    Eivind: INF100-102, har programmert en del på egen hånd, har lite erfaring med teamarbeid.

    Håkon: INF100-102, Har tidligere gjort prosjekt organiseringsarbeid for animasjonsfilm, litt kompetanse for lyddesign. Kan programmer i python, java og litt C. Har tidligere programmert animasjon til 2D spill.

    Oscar: INF100 (Python), INF101 (Java), INF102 (Java). Lagde blant annet spill i INF101. Har jobbet en god del med gruppearbeid i sammenheng med fag, men ikke i forbindelse med programmering. 

    for mer dokumentasjon se https://drive.google.com/drive/folders/1z-dJcMcevs0fEWIUqwuQFmU-l6OMfCPK?usp=sharing

## A1:
- Git repoet er satt opp
- Roller:
    Felles:
        Utvikler
        Software Tester
    Individuel:
        Prosjekt organisator: Håkon
            organiserer prosjektet, kaller inn til møter og booker rom. Passer på at vi er klar til innleveringer.
        Animatør: Håkon.
            Lager animasjoner for karakterene.
        Level/karakter designer: Håkon
            Lager karakter sprites og kartet/byggninger/planter osv
        Lyddesigner: Eivind
            lager lydbilde og musikk
        GUIdesigner: Alf, Oscar
            Lager menyer og oppsett av bruker interfacen i spillet.
        Lead Software Developer(har ansvar for kode kvalitet): Eivind
            Passer på at koden er skrevet riktig og i god kvalitet
        Lead Test Developer(Har ansvar for test kvalitet og coverage): Alf
            Passer på at testene er skrevet riktig og at vi har bra testcoverage
        Build/Deploy ansvarlig: Håkon
            bygger build testene og det som må til for at man skal kunne deploye. 
        Maven Ansvarlig: Eivind, Oscar
            Har ansvaret om vi skulle få problemer med Maven
        Hovedansvar Dokumentasjon: Oscar
            Passe på at vi har alt vi trenger av dokumentasjon.

## A2:
- Prosjektbeskrivelse:
    Farming/life sim eks.(Stardew Valley, Animal Crossing). Kunne ha en bevegelig karakter. Karakteren skal ha en helse og stamina måler. Skal ha muligheten til å gjøre klar jord, plante planter, vanne, høste inn og selge. Kunne kjøpe frø, dyr?, klær?, mat?. Skal kunne bygge hus og få nye beboere i byen?

## A3:
- Metodikk:
    Test Driven Development
    Par Programmering
    Issue bords på GitLab
- Møter:
    Minst ett møte i uken.
- Kommunikasjon:
    Kommuniserer via Discord.
- Arbeidsfordeling:
    Bruker issue boards
    Foreløpig fordeling av programmeringsarbeid:
        Controller - Håkon.
        View - Eivind.
        Player - Oscar.
        World - Alf.
- Oppfølging av arbeid:
    Disskuterer på ukentlig møte, Eivind ser over og kommenterer på arbeid som er ferdig.
- Deling og oppbevaring av kommunikasjon:
    Vi bruker google drive og laster opp møtereferat til repoet. Kode blir oppbevart på GitLab.

Vi planlegger å bruke test driven development siden test coverage skal være høyt. Vi jobber derfor først med å bygge tester for så å bygge klassene og metodene vi trenger for å løse testene. Vi avholder ukentlige møter hvor vi ser på hva vi har gjort og hv som må gjøres til neste innlevering eller møte og så fordeler vi arbeidet.

- Mål for applikasjonen:
    Lage et spill som fungerer som vi syntes er gøy å spille.
- Krav til MVP:
    Spillkarakter
    Flytter seg ved input/tastetrykk
    Bevegelse stoppes av verdenen
    Inventory med en hakke og en tom
    Ting i inventory kan velges ved hjelp av tastetrykk
    Dersom hakken er valgt, kan noen tiles endres på
    Kamera følger spilleren
    Verden er større en det som synes på skjermen

- Bruker historier:
    Som spiller trenger jeg å vite hva som er spillkarakteren slik at jeg vet hvor i spillet jeg er
        - For å tilfredstille dette må vi lage en karakter sprite og bygge view klassen sånn at den kan vise hvor karakteren er i spillet

    Som utvikler trenger jeg å se hva som er i nærheten av en posisjon for å avgjøre om en karakter kan bevege seg i en retning
        - For å få til dette må vi bygge metoder som sjekker hva som vises på kartet og som bestemmer hva som skal gjøres med interaksjoner mellom karakter og omverden.

    Som travel spiller trenger jeg å kunne pause og lagre slik at jeg kan spille litt om gangen
        - For å få til dette må vi kunne lagre informasjon om den spill sessionen som er igang og en måte å hente opp den informasjonen senere.

    Som en spiller med et annet tastaturoppsett trenger jeg å endre på kontrolloppsettet slik at jeg kan spille komfortabelt
        - For å få til dette må vi ha en klasse som lagrer knapp oppsettet og som har metoder for å endre på dem.

    Som en spiller som hovedsakelig interesserer meg for farming-aspektet av spillet, ønsker jeg en spillmodus hvor jeg kan spille uten fiendtlige maskinstyrte karakterer (peaceful mode). 
        - Dette kan oppnås ved å ha en gamestate Enum som slår av deler av spillet.

    Som spiller ønsker jeg å bevege meg rundt i verden
        - For å opnå dette må vi bygge en Controller klasse og lage en metode for å håndtere inputs og bevege karakteren om den korrekte knappen blir trykket inn. Vi må også ha metoder for å bevege karakteren.

    Som spiller ønsker jeg å endre verdenen jeg er i
        - Må da bygge en annen controller metode som håndterer endring i kartet og lage metoder i world klassen som håndterer dette

## A4:
Koden kompilerer og kjører, se `README.md` for instruksjoner.

## A5:
Vi kan starte med det som ikke gikk helt som planlagt:

Heldigvis ikke så mye! Vi har slitt litt med merge conflicts, men dette har heldigvis løst seg. Ellers så har noen av oss hatt litt mindre problemer med nedlastning av maven, og å få ssh-nøkkelen til å funke. Disse problemene er heldigvis også løst nå. 


Ting som har fungert bra:

Vi synes vi har jobbet bra sammen. Vi har hatt produktive og hyggelige møter, og føler vi ligger bra an med prosjektet hittil. Selv om prosjektet er i oppstartsfasen, har alt gått slik det var tiltenkt hittil. Vi har også brukt en del parprogrammering, og synes det er effektivt og lærerikt. Vi er også særlig fornøyd med at vi kan bevege karakteren rundt på brettet allerede!

Nye aktiviteter / verktøy vi har lyst til å prøve neste oblig:

- Få en oversikt over hvordan alt interagerer i koden
- Begynne med animering
- Anvende SpotBugs

Alt i alt vil vi si oss fornøyd hittil, og krysser fingrene for at alt av programvare fortsetter å fungere uten problemer fremover. Vi føler vi har truffet bra på oppgaven, og gleder oss til å kunne utvikle spillet vårt videre. 
