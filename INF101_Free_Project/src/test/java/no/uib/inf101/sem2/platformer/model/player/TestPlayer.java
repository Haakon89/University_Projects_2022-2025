package no.uib.inf101.sem2.platformer.model.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

public class TestPlayer {

  @Test
  public void testHashCodeAndEquals() {
    Player t1 = Player.newPlayer('I');
    Player t2 = Player.newPlayer('I');
    Player t3 = Player.newPlayer('I').shiftedBy(1, 0);
    Player s1 = Player.newPlayer('I');
    Player s2 = Player.newPlayer('I').shiftedBy(0, 0);

    assertEquals(t1, t2);
    assertEquals(s1, s2);
    assertEquals(t1.hashCode(), t2.hashCode());
    assertEquals(s1.hashCode(), s2.hashCode());
    assertNotEquals(t1, t3);
  }
}
