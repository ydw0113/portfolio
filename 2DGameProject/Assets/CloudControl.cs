using UnityEngine;
using System.Collections;

public class CloudControl : MonoBehaviour {
	
	public GameObject cloudmove;
	float jumpForce = 300.0f;
	float walkForce = 30.0f;
	float maxWalkSpeed = 2.0f;
	Rigidbody2D rigid2D;
	Collider2D collider2D;
	GameObject block;
	GameObject block2;
	GameObject cat;
	float key = 1.0f;
	// Use this for initialization
	void Start () {
		this.cloudmove = GameObject.Find ("cloudmove");
		this.cat = GameObject.Find ("cat");
		this.block = GameObject.Find ("block");
		this.block2 = GameObject.Find ("block2");
		this.rigid2D = GetComponent<Rigidbody2D> ();
		this.collider2D = GetComponent<Collider2D> ();

	}
	
	// Update is called once per frame
	void Update () {
		float speedx = Mathf.Abs (this.rigid2D.velocity.x);
		if(speedx <this.maxWalkSpeed){
			this.rigid2D.AddForce(transform.right * key * this.walkForce);
		}
		if (key != 0) {
			//transform.localScale = new Vector3 (key, 1, 1);
		}

	}
	void OnCollisionExit2D(Collision2D other){
		if(other.gameObject.Equals(cat)){
			this.rigid2D.isKinematic = false;
			this.rigid2D.gravityScale = 1f;
			this.collider2D.isTrigger = true;
		}

	}
	void OnCollisionEnter2D(Collision2D other){
		if (other.gameObject.Equals (block)) {
			key = 1.0f;
		}
		else if (other.gameObject.Equals (block2)) {
			key = -1.0f;
		}

	}

}
