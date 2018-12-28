using UnityEngine;
using System.Collections;

public class Cl : MonoBehaviour {
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

		if (this.transform.position.y < -1) {
			Debug.Log ("a");
			key = 1;

		}else if (this.transform.position.y > 2.5) {
			key = -1;
		}
		if(key ==1)
			this.transform.Translate (0, 0.03f, 0);
		if(key ==-1)
			this.transform.Translate (0, -0.03f, 0);
		


	}
	void OnTriggerEnter2D(Collider2D other){



	}
}
