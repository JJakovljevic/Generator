/*****************************************************************/
/*   Generisano na osnovu templejta: abstractPanelDetail.ftl     */
/*****************************************************************/


package gui.forms.detailpanels;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class AbstractPanelDetail extends JPanel {

	public abstract boolean proveriIspravnostPodataka();

}