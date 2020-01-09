package acn.ecm;

import java.util.HashMap;

import matrix.db.Query;
import matrix.db.QueryIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.matrixone.apps.domain.DomainConstants;
import com.matrixone.apps.domain.util.ContextUtil;
import com.matrixone.apps.domain.util.PropertyUtil;
import matrix.db.BusinessObjectWithSelect;
import matrix.db.Context;
import matrix.db.JPO;
import matrix.util.Pattern;
import matrix.util.StringList;
import pss.constants.TigerConstants;

/**
 * @author Gladston
 */
public class ACNEmxPart_mxJPO {

    private static Logger logger = LoggerFactory.getLogger("acn.ecm.ACNEmxPart");

    /**
     *
     * @param context
     *            the eMatrix <code>Context</code> object.
     * @param args
     *            contains a packed HashMap containing objectId of object
     * @return StringList.
     * @since EngineeringCentral X3
     * @throws Exception
     *             if the operation fails.
     */
    @com.matrixone.apps.framework.ui.ExcludeOIDProgramCallable
    public static StringList excludeOIDCADusageAttribute(Context context, String args[]) throws Exception {

        HashMap programMap = (HashMap) JPO.unpackArgs(args);
        String parentObjectId = (String) programMap.get("objectId");
        
        String strObjectId =DomainConstants.EMPTY_STRING;
		String strCADUsage = PropertyUtil.getSchemaProperty(context, "attribute_ACN_CADUsage");
		
		Pattern typePattern = new Pattern(TigerConstants.TYPE_MCAD_MODEL);
        typePattern.addPattern(TigerConstants.TYPE_MCADDRAWING);
		
		StringList result = new StringList();
		 if (parentObjectId == null) {
            return (result);
		 }
		StringList slBusSelects = new StringList(DomainConstants.SELECT_ID);
		String objectWhere = "attribute[" + strCADUsage + "] == 'Manufacturing'";
		
		try{
		Query query = new Query();
		
		query.setBusinessObjectType(typePattern.getPattern());
		query.setBusinessObjectName(DomainConstants.QUERY_WILDCARD);
        query.setBusinessObjectRevision(DomainConstants.QUERY_WILDCARD);
		query.setVaultPattern(TigerConstants.VAULT_ESERVICEPRODUCTION);
		query.setWhereExpression(objectWhere);
		ContextUtil.startTransaction(context, true);

		QueryIterator queryIterator = query.getIterator(context, slBusSelects, (short) 100);

		while (queryIterator.hasNext()) {
                BusinessObjectWithSelect busWithSelect = queryIterator.next();
                strObjectId = busWithSelect.getSelectData(DomainConstants.SELECT_ID);
				result.add(strObjectId);
            }
			queryIterator.close();
		} catch(Exception e) {
			logger.error("Error in excludeOIDCADusageAttribute: ", e);
			throw e;
		} finally {
            if (ContextUtil.isTransactionActive(context)) {
                ContextUtil.commitTransaction(context);
            }
        }
		return result;
	}

}
