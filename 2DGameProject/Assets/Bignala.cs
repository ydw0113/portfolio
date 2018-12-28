using UnityEngine;
using System.Collections;
using UnityEngine.SceneManagement;
public class Bignala : MonoBehaviour {
	Rigidbody2D rigid2D;
	float walkForce = 20.0f;
	float maxWalkSpeed = 2.0f;
	float span =1.0f;
	float delta =0;
	int at= 0;
	float key;
	int c;
	GameObject cat;
	Scene s;
	// Use this for initialization
	void Start () {
		s = SceneManager.GetActiveScene();
		this.rigid2D = GetComponent<Rigidbody2D> ();
		this.cat = GameObject.Find ("cat");

	}

	// Update is called once per frame
	void Update () {

		if (this.transform.position.x < -5) {
			Debug.Log ("a");
			key = 1;
			c = 1;
		} else if (this.transform.position.x > 5) {
			key = -1;
			c = 2;
		}else if (this.transform.position.y > 5) {
			key = -1;
			c = 3;
		}else if (this.transform.position.y < -4) {
			key = -1;
			c = 4;
		}
		if (key == 1 && c == 1) {
			this.transform.Translate (0.03f, -0.03f, 0);
		}
		else if(key ==-1 && c==2)
			this.transform.Translate (-0.03f, 0.03f, 0);
		else if(key ==-1 && c==3)
			this.transform.Translate (-0.03f, 0, 0);
		else if (key ==-1 && c==4)
			this.transform.Translate (0, 0.03f, 0);


		if (key != 0) {
			transform.localScale = new Vector3 (1f*key, 1f, 1);
		}

	}
	void OnTriggerEnter2D(Collider2D other){
		if (other.gameObject.Equals (cat)) {
			SceneManager.LoadScene (s.name);
		}



	}
}
