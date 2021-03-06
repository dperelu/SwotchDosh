package ucm.dv.vdm.logic;

import java.util.HashMap;
import java.util.Map;

import ucm.dv.vdm.engine.Game;
import ucm.dv.vdm.engine.Image;

/**
 * This class serves as a provider of Resources, makes the code much cleaner and more flexible.
 * Singleton
 */
public class ResourceManager {

    /**
     * Private constructor. Creates a new Resource manager, initialize all maps and calls initResources
     * to fill them with all the images/resources (if there were any sounds etc.
     * @param g Game instance to initialize images.
     */
    private ResourceManager(Game g){ // Create the ResourceManager and initialize the resources
        _intF = new HashMap<String, Image>();
        _gb = new HashMap<String, Image>();
        _text = new HashMap<String, Image>();

        initResources(g);
    }

    /**
     * Initialize all the resources needed for the game. (Images, Sounds, Etc.)
     * @param g Game instance
     */
    private void initResources(Game g){ // We won't save it because we won't need it in the future (I hope)
        // Interfaces
        _intF.put("Background", g.getGraphics().newImage("backgrounds.png"));
        _intF.put("Arrows", g.getGraphics().newImage("arrowsBackground.png"));
        _intF.put("Buttons", g.getGraphics().newImage("buttons.png"));
        _intF.put("White", g.getGraphics().newImage("white.png"));

        // GameObjects
        _gb.put("Balls", g.getGraphics().newImage("balls.png"));
        _gb.put("Player", g.getGraphics().newImage("players.png"));

        // Text
        _text.put("GameOver", g.getGraphics().newImage("gameOver.png"));
        _text.put("HowToPlay", g.getGraphics().newImage("howToPlay.png"));
        _text.put("Instructions", g.getGraphics().newImage("instructions.png"));
        _text.put("PlayAgain", g.getGraphics().newImage("playAgain.png"));
        _text.put("Logo", g.getGraphics().newImage("switchDashLogo.png"));
        _text.put("TapToPlay", g.getGraphics().newImage("tapToPlay.png"));
        _text.put("Font", g.getGraphics().newImage("scoreFont.png"));
    }

    /**
     * ResourceManager provider. If the manager has not been created, creates it and then return
     * the result.
     * If it exists, just return it.
     * @return resourceManager
     */
    public static ResourceManager getResourceMan(Game g){
        if(_rscm == null){ // If resourcemanager does not exist, create it
            _rscm = new ResourceManager(g);
        }
        return _rscm; // Return the ResourceManager
    }

    /**
     * Function to access the interface Map. Returns the image with the key value provided.
     * @param n Key of the resource
     * @return Image saved with that key
     */
    public Image getInterface(String n){
        return _intF.get(n); // Returns null if the image does not exist
    }

    /**
     * Returns an Image saved in the map with the key provided.
     * @param n key name of the Image
     * @return Image associated to that key
     */
    public Image getGameObject(String n){
        return _gb.get(n); // Returns null if the image does not exist
    }

    /**
     * Function that returns an Image saved in a dictionary with a specific Key name
     * @param n Key name mapped to the Image
     * @return Image mapped to that Key
     */
    public Image getText(String n){
        return _text.get(n);
    }

    /**
     * Instance of ResourceManager
     */
    private static ResourceManager _rscm;

    //---------------Images------------------
    // Interface
    private Map<String, Image> _intF;

    // GameObjects/Sprites
    private Map<String, Image> _gb;

    // Text
    private Map<String, Image> _text;

    // Sounds
    // ...

    // Etc.
    // ...
}
