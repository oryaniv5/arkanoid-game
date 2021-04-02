//ID:205444805

import java.util.ArrayList;
import java.util.List;

/**
 * Class save every collidable object in game.
 * can help found next collision point of givven line.
 */
public class GameEnvironment {

    private List<Collidable> collidables;

    /**
     * create new game environment,
     * using array list to save collidable.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c collidable to add the game.
     */
    public void addCollideable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory line of movement
     * @return collision point if found, otherwise return null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Rectangle r;

        //list get all collision info found.
        List<CollisionInfo> listOfCollisionInfo = new ArrayList<>();

        //loop get all over collidable object,
        // and found collision point's.
        for (Collidable c : collidables) {

            //get collidable shape values.
            r = c.getCollisionRectangle();

            //if found collision point with object.
            if (trajectory.closestIntersectionToStartOfLine(r) != null) {

                //add collision info to list.
                CollisionInfo collisionInfo = new CollisionInfo(trajectory.closestIntersectionToStartOfLine(r), c);
                listOfCollisionInfo.add(collisionInfo);
            }
        }

        //if no collision point return null.
        if (listOfCollisionInfo.size() == 0) {
            return null;
        }

        //return the closet collision point.
        return trajectory.getClosestFromList(listOfCollisionInfo);


    }

    /**
     * method remove collideable from list.
     *
     * @param c to remove.
     */
    public void removeCollideable(Collidable c) {
        this.collidables.remove(c);
    }
}
