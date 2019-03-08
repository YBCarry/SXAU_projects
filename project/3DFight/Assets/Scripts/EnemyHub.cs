using UnityEngine;
using System.Collections;

public class EnemyHub : MonoBehaviour {

    public UISlider enemyHealthSlider;
    public UISprite enemyHealthBarSprite;
    public UISprite enemyHealthSprite;
    public UISprite enemyHeadBGSpeite;
    public UISprite enemyHeadSprite;

    public EBunnyStatus eBunnyStatus;
    public WidgetAttackController widgetAttackController;

    private GameObject closeEnemy;  //取得最近的怪物
    private GameObject player;  //主角
    private float distanceToCloseEnemy;  //距离

    void Start()
    {
        enemyHealthSlider = GameObject.Find("EnemyHealthBar").GetComponent<UISlider>();
        enemyHealthBarSprite = GameObject.Find("EnemyHealthBar").GetComponent<UISprite>();
        enemyHealthSprite = GameObject.Find("EnemyHealth").GetComponent<UISprite>();
        enemyHeadBGSpeite = GameObject.Find("EnemyHeadBG").GetComponent<UISprite>();
        enemyHeadSprite = GameObject.Find("EnemyHead").GetComponent<UISprite>();

        player = GameObject.FindWithTag("Player");

        widgetAttackController = player.GetComponent<WidgetAttackController>();

        HideEnemyHub();
    }

    void Update()
    {
        closeEnemy = widgetAttackController.GetCloseEnemy();
        if (closeEnemy != null)
        {
            distanceToCloseEnemy = Vector3.Distance(closeEnemy.transform.position, player.transform.position);

            if (distanceToCloseEnemy < 20)
            {
                ShowEnemyHub();
                eBunnyStatus = closeEnemy.GetComponent<EBunnyStatus>();
                enemyHealthSlider.value = eBunnyStatus.health / eBunnyStatus.maxHealth;
            }
        }
        else
        {
            HideEnemyHub();

        }
    }

    //隐藏怪物面板
    void HideEnemyHub()
    {
        enemyHealthBarSprite.enabled = false;
        enemyHealthSprite.enabled = false;
        enemyHeadBGSpeite.enabled = false;
        enemyHeadSprite.enabled = false;
    }

    //显示怪物面板
    void ShowEnemyHub()
    {
        enemyHealthBarSprite.enabled = true;
        enemyHealthSprite.enabled = true;
        enemyHeadBGSpeite.enabled = true;
        enemyHeadSprite.enabled = true;
    }

}
