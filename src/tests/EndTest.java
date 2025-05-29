package tests;

import command.End;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
public class EndTest {

    End end = new End();
    @org.junit.jupiter.api.Test
    void execute(){
        assertEquals("Closing game...",end.execute());
    }
}
