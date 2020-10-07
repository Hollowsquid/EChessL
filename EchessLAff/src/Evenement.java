import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Evenement implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
        if ( SwingUtilities.isLeftMouseButton(e) ){

            EchessL.pieceSelectLigne = e.getY()/EchessL.dimRect;
            EchessL.pieceSelectColonne = e.getX()/EchessL.dimRect;

        }else if(SwingUtilities.isRightMouseButton(e)){
            //System.out.println("Clic droit");
            EchessL.clicDroit(e.getX(),e.getY());

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}