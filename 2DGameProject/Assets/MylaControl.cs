using UnityEngine;
using System.Collections;

public class MylaControl : MonoBehaviour {
	Rigidbody2D rigid2D;
	float walkForce = 20.0f;
	float maxWalkSpeed = 2.0f;
	float span =1.0f;
	float delta =0;
	int at= 0;
	int key;
	// Use this for initialization
	void Start () {
		this.rigid2D = GetComponent<Rigidbody2D> ();

	}
	
	// Update is called once per frame
	void Update () {

		if (this.transform.position.x < -1.7) {
			Debug.Log ("a");
			key = 1;

		}if(key ==1)
		this.transform.Translate (0.05f, 0, 0);
		if(key ==-1)
		this.transform.Translate (-0.05f, 0, 0);
		else if (this.transform.position.x > 1) {
			key = -1;
		}

		if (key != 0) {
			transform.localScale = new Vector3 (key, 1, 1);
		}

	
	}
	void OnTriggerEnter2D(Collider2D other){

			

	}
}
