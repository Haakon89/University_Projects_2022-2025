package no.uib.inf101.sem2.platformer.model;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.Grid;

public class PlatformerBoard extends Grid<Character> {
    
    

    public PlatformerBoard() throws IOException {
        super('-', 'W', 'R', 'G', 'O', 'D', ImageIO.read(new File("src/main/java/no/uib/inf101/sem2/LevelLayouts/Tutorial_collision_map.png")));
        
    }

}
