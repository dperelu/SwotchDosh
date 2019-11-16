package ucm.dv.vdm.pcengine;

import java.awt.*;
import java.awt.image.BufferStrategy;

import ucm.dv.vdm.engine.Rect;

public class Graphics implements ucm.dv.vdm.engine.Graphics {

    public void testCanvas(Window w){
        _win.getJGraphics().setColor(Color.pink);
        _win.getJGraphics().fillRect(_can.getX(), _can.getY(), _can.getWidth(), _can.getHeight());
    }

    /**
     * Graphics constructor. Saves an instance of the window.
     * @param w Window instance
     */
    public Graphics(Window w) {
        _win = w;
        _can = new Rect(0, 0, 0, 0);
        _refCan = new Rect(0, 0, 0, 0);
    }

    /**
     * Set a size for the canvas to place objects in the menus and UI
     * @param c Size of canvas
     */
    @Override
    public void setCanvasSize(Rect c) {
        _can = c;
    }

    @Override
    public void setReferenceCanvas(Rect c) {
        _refCan = c;
    }

    //Hacer dos métodos que inicien y acaben el frame
    // Esto es para abstraer t odo el sistema de pintado de la Lógica

    /**
     * Creates an Image (type Image from pcengine) and returns it.
     * @param name The path to the image
     * @return The image created
     */
    @Override
    public Image newImage(String name) {
        Image _image = null; // Temporal variable to store the Image

        try { // Try to create the image and if it fails handle the exception
            _image = new Image((java.awt.Image) javax.imageio.ImageIO.read(new java.io.File(name)));

        } catch (Exception e) {
            System.err.println(e);
        }

        // Return the image created
        return _image;
    }

    /**
     * This function receives a color and paints the hole screen with that color (white recommended)
     * to clean it from the last painting.
     * @param color Flat color received to paint the screen
     */
    @Override
    public void clear(int color) {
        // Set color to paint in the Swing Graphics.
        _win.getJGraphics().setColor(Color.BLACK);
        // Paint the hole screen with it.
        _win.getJGraphics().fillRect(0, 0, _win.getWidth(), _win.getHeight());
    }

    /**
     * Draws an image (or a part of it (Sprite)) in a specific location, counting with the values of
     * the image.
     * @param image Image to paint
     * @param source Image dimensions (Rectangle)
     * @param x Position X to place the image (top left corner)
     * @param y Position Y to place the image (top left corner)
     */
    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, Rect source, int x, int y) {
        try {
            Rect temp = new Rect (repositionX(source.getWidth()), 0, 0, repositionY(source.getHeight()));
            source = dimensions(source, temp);

            x = _can.getX() + repositionX(x);
            y = _can.getY() + repositionY(y);

            if (image != null) { // If the image exists, try to draw it
                _win.getJGraphics().drawImage(((Image) image).getImage(), x, y, _can.getX() + source.getWidth(), _can.getY() + source.getHeight(),
                        source.getLeft(), source.getTop(), source.getRight(), source.getBottom(), null);
            }
        } catch (Exception e) { // Handle Exception

        }
    }

    /**
     * Draw an image (or a part of it) in a specific rectangle location.
     * @param image Image to paint
     * @param source Image dimensions
     * @param dest Rectangle in which we will draw
     */
    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, Rect source, Rect dest) {
        try {
            dest = dimensions(dest, _can);
            source = dimensions(source, dest);

            dest.setPosition(_can.getX() + repositionX(dest.getX()), _can.getY() + repositionY(dest.getY()));

            if (image != null) {
                _win.getJGraphics().drawImage(((ucm.dv.vdm.pcengine.Image) image).getImage(), dest.getX(), dest.getY(), _can.getX() + dest.getWidth(), _can.getY() + dest.getHeight(),
                        source.getLeft(), source.getTop(), source.getRight(), source.getBottom(), null);
            }
        } catch (Exception e) {

        }
    }

    /**
     * Draws an image (or a part of it (Sprite)) in a specific rectangle with an specific alpha
     * value (transparency).
     * @param image Image to paint
     * @param source Rectangle of the image.
     * @param dest Rectangle destination to paint the image.
     * @param alpha Alpha value for the color.
     */
    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, Rect source, Rect dest, int alpha) {

    }

    /**
     * Draws an image in a specific location.
     * @param image Image that wil be painted
     * @param x Position x of the image
     * @param y Position y of the image
     */
    @Override
    public void drawImage(ucm.dv.vdm.engine.Image image, int x, int y) {



        try {
            if (image != null) { // If the image exists, try to draw it
                _win.getJGraphics().drawImage(((Image) image).getImage(), 0, 100, null);
            }
        } catch (Exception e) { // Handle Exception

        }
    }

    /**
     * Return the width value of the window.
     * @return Window Width
     */
    @Override
    public int getWidth() {
        return _win.getWidth();
    }

    /**
     * Return the height value of the window.
     *
     * @return Window height
     */
    @Override
    public int getHeight() {
        return _win.getHeight();
    }

    /**
     * DIMENSIOOOOOOOOOOOOOOOOOOOOOOOOOOOOOn
     */
    @Override
    public Rect dimensions(Rect src, Rect dim){
        Rect temp; // Temporal rectangle for calculations

        int width = src.getWidth(); //
        int height = src.getHeight();

        if(width > dim.getWidth()){
           width = dim.getWidth();

           height = (width * src.getHeight()) / src.getWidth();
        }

        if(height > dim.getHeight()){
            height = dim.getHeight();

            width = (height * src.getWidth()) / src.getHeight();
        }

        temp = new Rect (width, 0, 0, height);

        return temp;
    }

    @Override
    public void setCanvasPos(int x, int y) {
        _can.setPosition(x, y);
    }

    @Override
    public int repositionX(int x) {
        return (x * _can.getWidth()) / _refCan.getWidth();
    }

    @Override
    public int repositionY(int y) {
        return (y * _can.getHeight()) / _refCan.getHeight();
    }

    //---------------------------------------------------------------
    //----------------------Pivate Atributes-------------------------
    //---------------------------------------------------------------

    /**
     * Atribute that saves an instance of the window to get the Swing Graphics
     */
    Window _win;

    // Canvas
    Rect _can;

    Rect _refCan;

}
