using UnityEngine;
using System.Collections;
using UnityEngine.SceneManagement;
public class ArrowController : MonoBehaviour {
	// Use this for initialization
	GameObject cat;
	float c=0;
	void Start () {
		this.cat = GameObject.Find ("cat");
	}
	
	// Update is called once per frame
	void Update () {
		if(transform.position.y < -5.0f){
			Destroy(gameObject);
		}
	}
	void OnTriggerEnter2D(Collider2D other){
		

		if (other.gameObject.Equals (cat)) {
			GameObject director = GameObject.Find("GameDirector");
			director.GetComponent<Hp>().DecreaseHp();
			Destroy (gameObject);
		}
	}

}
