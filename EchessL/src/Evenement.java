import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Evenement implements MouseListener
{
    @Override
    public void mouseClicked(MouseEvent e) 
    {
        if (e.getButton()==MouseEvent.BUTTON1){
            System.out.print("Colonne : ");
            System.out.println(e.getX()/EchessL.dimRect);
            System.out.print("Ligne : ");
            System.out.println(e.getY()/EchessL.dimRect);

            EchessL.pieceSelectLigne = e.getY()/EchessL.dimRect;
            EchessL.pieceSelectColonne = e.getX()/EchessL.dimRect;

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}