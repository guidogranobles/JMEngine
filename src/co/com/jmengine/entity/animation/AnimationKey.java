package co.com.jmengine.entity.animation;

import java.util.ArrayList;

public class AnimationKey {

	 public ArrayList<MatrixAnimationKey> matrixAnimationsKeys = new ArrayList<MatrixAnimationKey>();
	 public ArrayList<RotationAnimationKey> rotationAnimationsKeys = new ArrayList<RotationAnimationKey>();
	 public ArrayList<ScalingAnimationKey> scalingAnimationsKeys = new ArrayList<ScalingAnimationKey>();
	 public ArrayList<TranslationAnimationKey> translationAnimationsKeys = new ArrayList<TranslationAnimationKey>();
	 
	 public int type;
}
