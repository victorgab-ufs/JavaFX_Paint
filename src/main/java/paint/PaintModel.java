package paint;

import java.util.ArrayList;
import java.util.List;

public class PaintModel {

    private List<Figure> figures = new ArrayList<>(); 

    public void addFigure(Figure f) {
        figures.add(f);
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public Figure getSelectedFigure() {
        for (Figure f : figures){
            if (f.isSelected()) return f;
        }
        return null;
    }

    public void eraseFigure(Figure selectedFigure){
        figures.remove(selectedFigure);
    }

    public void toBack(Figure selectedFigure){
        if (figures.remove(selectedFigure)){
            figures.add(0, selectedFigure);
        }
    }

    public void toForth(Figure selectedFigure){
        if (figures.remove(selectedFigure)){
            figures.add(selectedFigure);
        }
    }
}
