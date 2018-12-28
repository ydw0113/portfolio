using UnityEngine;
using System.Collections;

public class ArrowGenerate : MonoBehaviour {

	public GameObject arrowPrfab;
	float span =1.0f;
	float delta =0;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		this.delta += Time.deltaTime;
		if (this.delta > this.span) {
			this.delta = 0;
			GameObject go = Instantiate (arrowPrfab) as GameObject;
			GameObject go2 = Instantiate (arrowPrfab) as GameObject;
			int px = Random.Range (-6, 0);
			int px2 = Random.Range (0, 7);
			go.transform.position = new Vector3 (px, 20, 0);
			go2.transform.position = new Vector3 (px2, 20, 0);
		
		}
	
	}

}
