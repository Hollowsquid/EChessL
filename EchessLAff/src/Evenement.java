import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Evenement implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
        if ( SwingUtilities.isLeftMouseButton(e) ){
//            System.out.print("Colonne : ");
//            System.out.println(e.getX()/EchessL.dimRect);
//            System.out.print("Ligne : ");
//            System.out.println(e.getY()/EchessL.dimRect);

            EchessL.pieceSelectLigne = e.getY()/EchessL.dimRect;
            EchessL.pieceSelectColonne = e.getX()/EchessL.dimRect;

        }else if(SwingUtilities.isRightMouseButton(e)){
            //System.out.println("Clic droit");
            if(EchessL.pieceSelectColonne >= 0 && EchessL.pieceSelectLigne >= 0 && EchessL.echiquier[EchessL.pieceSelectLigne][EchessL.pieceSelectColonne].equipe == EchessL.tourActuel){
                EchessL.deplacementAccepter = true;
                EchessL.deplacer(EchessL.pieceSelectLigne, EchessL.pieceSelectColonne, e.getY() / EchessL.dimRect, e.getX() / EchessL.dimRect, EchessL.echiquier);
                if(EchessL.deplacementAccepter){
                    EchessL.tourActuel *= -1;
                    int gagnant = EchessL.ConditionVictoire(EchessL.echiquier);
                    if(gagnant != 0){
                        System.out.println("Victoire de l'équipe "+gagnant+" !");
                        EchessL.initialisationEchiquier();
                    }
                }
                EchessL.pieceSelectColonne = -1;
                EchessL.pieceSelectLigne = -1;
            }
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