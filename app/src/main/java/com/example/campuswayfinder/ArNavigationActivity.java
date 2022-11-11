package com.example.campuswayfinder;

import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.ar.core.Anchor;
import com.google.ar.core.Frame;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Pose;
import com.google.ar.core.Trackable;
import com.google.ar.core.TrackingState;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
;

public class ArNavigationActivity extends AppCompatActivity{

    private ArNavigationFragment arFragment;
    private ArrayList anchorList;
    public Spinner modelOptionsSpinner;
    private static final String[] paths = {"Straight Arrow", "Right Arrow", "Left Arrow"};
    private String SCANNED_LOCATION;

    private enum AppAnchorState {
        NONE,
    }
    private Anchor anchor;
    private AnchorNode anchorNode;
    private boolean isPlaced = false;
    private AppAnchorState appAnchorState = AppAnchorState.NONE;
    private Integer Directions[] = {0, 1, 2};

    // assigned when invoking the createPose() method
    ArrayList<Pose> PoseList = new ArrayList<>();

    private ModelRenderable arrowRenderable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                SCANNED_LOCATION = null;
            } else {
                SCANNED_LOCATION = extras.getString(StartIndoorActivity.SCANNED_LOCATION);
            }
        }
        setContentView(R.layout.activity_ar_view);
        arFragment = (ArNavigationFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);

        // initiate renderable features
        loadRenderableModel();
        assert arFragment != null;
        arFragment.getArSceneView().getScene().addOnUpdateListener(this::onUpdateFrame);

       /* arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            Log.d("HIT_RESULT:", hitResult.toString());
            anchor = Objects.requireNonNull(arFragment.getArSceneView().getSession()).hostCloudAnchor(hitResult.createAnchor());
            appAnchorState = AppAnchorState.HOSTING;
            isPlaced = true;
            createCloudAnchorModel(anchor,Directions[0]);
        });*/
    }

    private void onUpdateFrame(FrameTime frameTime) {

        Frame frame = arFragment.getArSceneView().getArFrame();
        // If there is no frame, just return.
        if (frame == null) {
            return;
        }
        // create the path anchors to chosen scanned indoor locations
        if(SCANNED_LOCATION.equalsIgnoreCase(StartIndoorActivity.LIB_BASE_001)) {
            Log.d("result string", SCANNED_LOCATION);
        }else if(SCANNED_LOCATION.equalsIgnoreCase(StartIndoorActivity.LIB_BASE_002)){
        }
        //Making sure ARCore is tracking some feature points, makes the augmentation little stable.
        /*if(frame.getCamera().getTrackingState()== TrackingState.TRACKING && !isPlaced) {
            arFragment.getPlaneDiscoveryController().hide();
            //float[] pos = { 0, 0, -1 };
            //float[] rotation = { 0, 0, 0, 1 };
            Pose pos = frame.getCamera().getPose().compose(Pose.makeTranslation(0, 0.5f, 0f));
            anchor = Objects.requireNonNull(Objects.requireNonNull(arFragment.getArSceneView().getSession()).createAnchor(pos));
            appAnchorState = AppAnchorState.HOSTING;
            //Anchor anchor = arFragment.getArSceneView().getSession().createAnchor(new Pose(pos, rotation));
            createCloudAnchorModel(anchor,Directions[0]);
        }*/

        for (Plane plane : frame.getUpdatedTrackables(Plane.class)) {
            if ( plane.getType() == Plane.Type.HORIZONTAL_UPWARD_FACING &&
                    plane.getTrackingState() == TrackingState.TRACKING && !isPlaced)  {
                // mark the plane has been found
                // isPlaced = true;

                // attach pose to anchor and set footRenderable then print the route
                ArrayList<AnchorNode> AnchorNodeList = new ArrayList<>();
                ArrayList<Node> NodeList = new ArrayList<>();
                assert PoseList != null;

                ModelRenderable modelRenderable = arrowRenderable;
                anchorNode = new AnchorNode(Objects.requireNonNull(arFragment.getArSceneView().getSession()).createAnchor(plane.getCenterPose()));
                anchorNode.setParent(arFragment.getArSceneView().getScene());
                Node arrowNode = new Node();
                arrowNode.setParent(anchorNode);
                arrowNode.setRenderable(modelRenderable);
            }
        }
       /* if(!isPlaced)
        {
            arFragment.getArSceneView().getPlaneRenderer().setEnabled(true);
            arFragment.getPlaneDiscoveryController().show();

            //get the trackables to ensure planes are detected
            for (Plane plane : frame.getUpdatedTrackables(Plane.class)) {
                //If a plane has been detected & is being tracked by ARCore
                if ( plane.getType() == Plane.Type.HORIZONTAL_UPWARD_FACING &&
                        plane.getTrackingState() == TrackingState.TRACKING) {

                    // mark the plane has been found
                    // isPlaced = true;

                    // create a route in the form of Pose
                    PoseList = createPose();

                    // attach pose to anchor and set footRenderable then print the route
                    ArrayList<AnchorNode> AnchorNodeList = new ArrayList<>();
                    ArrayList<Node> NodeList = new ArrayList<>();
                    assert PoseList != null;

                    for (int i = 0; i < PoseList.size(); i++) {
                        ModelRenderable modelRenderable = arrowRenderable;
                        AnchorNodeList.add(i, new AnchorNode(Objects.requireNonNull(arFragment.getArSceneView().getSession()).createAnchor(plane.getCenterPose())));
                        //(PoseList.get(i))));
                        AnchorNodeList.get(i).setParent(arFragment.getArSceneView().getScene());
                        NodeList.add(i, new Node());
                        NodeList.get(i).setParent(AnchorNodeList.get(i));
                        NodeList.get(i).setRenderable(modelRenderable);
                    }
                }
            }
        }
    */
    }

    /**
     * create a series of pose for routing, using self-deviced algorithm
     * to map the virtual world coordinates to the real world's.
     */
    private  ArrayList<Pose> createPose() {

        ArrayList<Pose> poseList = new ArrayList<>();
        float[] pos1 = { 0, 0, 0 };
        float[] rot1 = { 0, 0, 0, 0 };

        // place the first point and determine its direction
        poseList.add(0, Pose.makeTranslation(0, 0, -1f));

        //poseList.add(new Pose(pos1, rot1));
        // currentObstaclePose = hit.getHitPose().extractTranslation().compose(frame.getCamera().getPose().extractRotation())
        return poseList;
    }

    private void loadRenderableModel() {
        ModelRenderable
                .builder()
                .setSource(this, Uri.parse("model.sfb"))
                .build()
                .thenAcceptAsync(modelRenderable -> arrowRenderable = modelRenderable)
                .exceptionally(throwable -> {
                    Toast toast =
                            Toast.makeText(this, "Unable to load andy renderable", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return null;
                });
    }




    private void createCloudAnchorModel(Anchor anchor, Integer DirectionChoice) {
        ModelRenderable
                .builder()
                .setSource(this, Uri.parse("model.sfb"))
                .build()
                .thenAccept(modelRenderable -> placeCloudAnchorModel(anchor, modelRenderable, DirectionChoice))
                .exceptionally(throwable -> {
                    Toast toast =
                            Toast.makeText(this, "Unable to load andy renderable", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return null;
                });
    }
    private void placeCloudAnchorModel(Anchor anchor, ModelRenderable modelRenderable, Integer DirectionChoice) {
        
        anchorNode = new AnchorNode(anchor);
        anchorNode.setParent(arFragment.getArSceneView().getScene());

        //AnchorNode cannot be zoomed in or moved
        Node arrow = new Node();
        switch (DirectionChoice) {
            case 1: // Right
                arrow.setLocalRotation(Quaternion.axisAngle(new Vector3(0, 1f, 0), 135));
                break;
            case 2: // Left
                arrow.setLocalRotation(Quaternion.axisAngle(new Vector3(0, 1f, 0), 315));
                break;
            default: // Straight
                arrow.setLocalRotation(Quaternion.axisAngle(new Vector3(0, 1f, 0), 225));
                break;
        }

        arrow.setParent(anchorNode);
        arrow.setRenderable(modelRenderable);
        arrow.setLocalPosition(new Vector3(0f, 0f, 0f));
        isPlaced = true;
        //adding this to the scene
        arFragment.getArSceneView().getScene().addChild(anchorNode);
    }
}
