//ID:205444805

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * class create game.
 * using sprite and coolidable collection to
 * identify screen object and movement of object.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private biuoop.KeyboardSensor keyboard;
    private Counter remainedBlocks;
    private Counter remainedBalls;
    private Counter scoreCounter;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation lif;
    private ScoreIndicator scoreIndicator;

    private static final int BALL_RADIOS = 3;
    private static final int FRAME_BLOCK_SIZE = 20;
    private static final int ZERO_POINTS = 0;
    private static final int GAME_WIDTH = 800;
    private static final int GAME_HEIGHT = 600;

    /**
     * create new gmae object, include
     * GameEnvironment and SpriteCollection object
     * to control screen.
     *
     * @param lif the level info to show
     * @param ks  Keyboard Sensor
     * @param ar  to run gmae
     * @param gui screen
     * @param si  Score Indicator
     */
    public GameLevel(LevelInformation lif, KeyboardSensor ks, AnimationRunner ar, GUI gui, ScoreIndicator si) {
        this.gui = gui;
        this.keyboard = ks;
        this.runner = ar;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.lif = lif;
        this.remainedBlocks = new Counter(this.lif.numberOfBlocksToRemove());
        this.remainedBalls = new Counter(this.lif.numberOfBalls());
        this.scoreCounter = si.getScoreCounter();
        this.scoreIndicator = si;
        this.scoreIndicator.setLevelName(this.lif.levelName());
        ;
    }

    /**
     * Add new collidable object to screen.
     *
     * @param c collidable object.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollideable(c);
    }

    /**
     * Add new sprite object to screen.
     *
     * @param s sprite object.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);

    }

    /**
     * Create the block of the game
     * and add them into the game.
     */
    public void createGameBlocks() {

        //create listeners.
        ScoreTrackingListener stl = new ScoreTrackingListener(this.scoreCounter);
        BlockRemover br = new BlockRemover(this, this.remainedBlocks);

        for (Block block : this.lif.blocks()) {
            //add listeners to block.
            block.addHitListener(br);
            block.addHitListener(stl);

            //add block to game.
            block.addToGame(this);

        }
    }

    /**
     * create games frame block.
     */
    public void createFrameBlocks() {

        //create for every frame block.
        //up frame block
        Block up = new Block(new Rectangle(new Point(0, 20), GAME_WIDTH, FRAME_BLOCK_SIZE), Color.GRAY);
        up.addToGame(this);

        //left frame block
        Block left = new Block(new Rectangle(new Point(0, 20), FRAME_BLOCK_SIZE, GAME_HEIGHT), Color.GRAY);
        left.addToGame(this);

        //right frame block
        Block right = new Block(new Rectangle(new Point(780, 20), FRAME_BLOCK_SIZE, GAME_HEIGHT), Color.GRAY);
        right.addToGame(this);

        //down frame block
        Block down = new Block(new Rectangle(new Point(0, 600), GAME_WIDTH, FRAME_BLOCK_SIZE), Color.GRAY);
        BallRemover ballRemover = new BallRemover(this, this.remainedBalls);
        down.addHitListener(ballRemover);
        down.addToGame(this);


    }

    /**
     * create the balls in game
     * and add them into the game.
     */
    public void createGameBalls() {

        int paddleWidth = lif.paddleWidth();
        int ballsNumber = lif.numberOfBalls();
        for (int i = 0; i < ballsNumber; i++) {
            Point middleOfPaddle = new Point(400, 555);
            Ball b = new Ball(middleOfPaddle, BALL_RADIOS, Color.WHITE);
            b.setVelocity(this.lif.initialBallVelocities().get(i));
            b.setGame(this.environment);
            b.addToGame(this);
        }
    }

    /**
     * Initialize a new game: create the blocks ,ball and Paddle.
     * and add them to the game.
     */
    public void initialize() {

        //add background to game sprit.
        this.sprites.addSprite(this.lif.getBackground());

        //create game balls.
        this.createGameBalls();

        //create game blocks.
        this.createGameBlocks();

        //create game frame blocks.
        this.createFrameBlocks();

        //create paddle.
        Paddle p = new Paddle(this.gui, lif.paddleSpeed(), lif.paddleWidth());
        p.addToGame(this);

        //create score indicator.
        this.sprites.addSprite(this.scoreIndicator);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        CountdownAnimationRunner car = new CountdownAnimationRunner(this.gui, 3);
        car.run(new CountdownAnimation(3, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * remove collidable object from list.
     *
     * @param c to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollideable(c);
    }

    /**
     * remove sprite object from list.
     *
     * @param s to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed("p")) {
//            this.runner.run(new PauseScreen(this.keyboard));
            PauseScreen ps = new PauseScreen();
            KeyPressStoppableAnimation kpsa = new KeyPressStoppableAnimation(this.keyboard, keyboard.SPACE_KEY, ps);
            this.runner.run(kpsa);

        }
        //if all block remove, add 100 points to score,
        // and close gui.
        if (this.remainedBlocks.getValue() == 0) {
            this.scoreCounter.increase(100);
            this.running = false;
//            this.gui.close();
        }

        //if all balls remove close gui.
        if (this.remainedBalls.getValue() == 0) {
            this.running = false;
//            this.gui.close();
        }
        //draw all sprite.
        this.sprites.drawAllOn(d);

        //notify all sprite that time passed.
        this.sprites.notifyAllTimePassed();
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * decide if remain balls in game.
     *
     * @return true if remain balls, false otherwise.
     */
    public boolean remainBalls() {
        if (this.remainedBalls.getValue() == 0) {
            return false;
        }
        return true;
    }

    /**
     * decide if remain blocks in game.
     *
     * @return true if remain cloks, false otherwise.
     */
    public boolean remainBlocks() {
        if (this.remainedBlocks.getValue() == 0) {
            return false;
        }
        return true;
    }

}