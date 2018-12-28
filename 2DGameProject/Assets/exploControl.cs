using UnityEngine;
using System.Collections;

public class exploControl : MonoBehaviour {
	float span =1.0f;
	float delta2 =0;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		this.delta2 += Time.deltaTime;

		if (this.delta2 > 1) {
			Debug.Log (delta2);

			this.delta2 = 0;
		}
	}
	
}

