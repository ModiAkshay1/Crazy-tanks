package com.crazytanks.game.utils;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolylineMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
public class tiledobject {
    public static void parseTiledObjectLayer(World world, MapObjects objects) {
        for(MapObject object : objects) {
            //System.out.println(object.getClass());
            //System.out.println("dnbdasjb");
            Shape shape;
            if(object instanceof PolylineMapObject) {
                shape = createPolyline((PolylineMapObject) object);
                System.out.println(shape);
            } else {
                //System.out.println("dnbdasjb");
                continue;
            }

            Body body;
            BodyDef bdef = new BodyDef();
            bdef.type = BodyDef.BodyType.StaticBody;
            body = world.createBody(bdef);
            body.createFixture(shape, 1.0f);
            shape.dispose();
        }
    }

    private static ChainShape createPolyline(PolylineMapObject polyline) {
        float[] vertices = polyline.getPolyline().getTransformedVertices();

        Vector2[] worldVertices = new Vector2[vertices.length / 2];


        for(int i = 0; i < worldVertices.length; i++) {
            //System.out.println("jhgdjh");
            worldVertices[i] = new Vector2(vertices[i * 2] / 100, vertices[i * 2 + 1] / 100);

        }
        ChainShape cs = new ChainShape();

        cs.createChain(worldVertices);
        //System.out.println(cs.getVertexCount());
        return cs;
    }
}
