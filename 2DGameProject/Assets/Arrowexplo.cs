using UnityEngine;
using System.Collections;
using UnityEngine.SceneManagement;
public class Arrowexplo : MonoBehaviour {
	public GameObject exploP;
	float span =1.0f;
	float delta2 =0;
	static GameObject go;
	GameObject exp;
	GameObject nala;

	// Use this for initialization
	void Start () {
		this.nala = GameObject.Find ("nala");

	}


	// Update is called once per frame
	void Update () {
		this.delta2 += Time.deltaTime;

		if (this.delta2 > 1) {
			//Debug.Log (delta2);

			Destroy (go);

			this.delta2 = 0;
		}
	}
	void OnTriggerEnter2D(Collider2D other){
			go = Instantiate (exploP) as GameObject;
			go.transform.position = new Vector3 (this.transform.position.x, this.transform.position.y, 0);

	}
	public GameObject ex(){
		return go;
	}

}
