/*
 *  Java Iris Rest Client, pratical example for use IRIS REST API
 * 
 *  Copyright (c) 2015, CINECA and third-party contributors as
 *  indicated by the @author tags or express copyright attribution
 *  statements applied by the authors.  All third-party contributions are
 *  distributed under license by CINECA.
 * 
 *  This copyrighted material is made available to anyone wishing to use, modify,
 *  copy, or redistribute it subject to the terms and conditions of the GNU
 *  Lesser General Public License v3 or any later version, as published 
 *  by the Free Software Foundation, Inc. <http://fsf.org/>.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 *  or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 *  for more details.
 * 
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this distribution; if not, write to:
 *  Free Software Foundation, Inc.
 *  51 Franklin Street, Fifth Floor
 *  Boston, MA  02110-1301  USA
 */
package it.cineca.iris.ir.rest.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemRestDTO extends DSpaceObject {
     
    private ContainerDTO collection;
    private String collectionHandle;
    private int simpleItemStatus;
    private int submitterID;
    private String submitterNetID;
    private boolean archived;
    private boolean inWorkFlow;
    private boolean inWorkspace;
    private boolean snapshot;	
    private boolean withdrawn;
    private boolean inarchive;
    private boolean earlyDraft;
    private int itemId;
    private String identifier;
    private int wrapperId;
    private Date lastModified;
    private String lastModifiedISO;
    private EPersonDTO submitter;
    private EPersonDTO owner;
    private int workflowState;
    private List<String> actions;
    private int workFlowValidationRule;	
    private boolean reopened;
    private int workFlowValidationStatus;
    private int identifierToDisseminate;
    private int inputFormId;

    private Map<String, List<MetadataEntryRestDTO>> metadata;
    private List<BitstreamRestDTO> bitstreams;
    private List<EventLogRestDTO> historyLog;
    private Map<String, String> lookupValues;
    private List<InternalAuthorRestDTO> internalAuthors;

    public ContainerDTO getCollection() {
        return collection;
    }

    public void setCollection(ContainerDTO collection) {
        this.collection = collection;
    }

    public String getCollectionHandle() {
        return collectionHandle;
    }

    public void setCollectionHandle(String collectionHandle) {
        this.collectionHandle = collectionHandle;
    }

    public int getSimpleItemStatus() {
        return simpleItemStatus;
    }

    public void setSimpleItemStatus(int simpleItemStatus) {
        this.simpleItemStatus = simpleItemStatus;
    }
    
    public int getSubmitterID() {
        return submitterID;
    }

    public void setSubmitterID(int submitterID) {
        this.submitterID = submitterID;
    }

    public String getSubmitterNetID() {
        return submitterNetID;
    }

    public void setSubmitterNetID(String submitterNetID) {
        this.submitterNetID = submitterNetID;
    }
    
    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
    
    public boolean isInWorkFlow() {
        return inWorkFlow;
    }

    public void setInWorkFlow(boolean inWorkFlow) {
        this.inWorkFlow = inWorkFlow;
    }

    public boolean isInWorkspace() {
        return inWorkspace;
    }

    public void setInWorkspace(boolean inWorkspace) {
        this.inWorkspace = inWorkspace;
    }
    
    public boolean isSnapshot() {
        return snapshot;
    }

    public void setSnapshot(boolean snapshot) {
        this.snapshot = snapshot;
    }

    public boolean isWithdrawn() {
        return withdrawn;
    }

    public void setWithdrawn(boolean withdrawn) {
        this.withdrawn = withdrawn;
    }

    public boolean isInarchive() {
        return inarchive;
    }

    public void setInarchive(boolean inarchive) {
        this.inarchive = inarchive;
    }
    
    public boolean isEarlyDraft() {
        return earlyDraft;
    }

    public void setEarlyDraft(boolean earlyDraft) {
        this.earlyDraft = earlyDraft;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int id) {
        this.itemId = id;
    }
    
    @Override
	public int getId() {
		return itemId;
	}
    
	@Override
	public void setId(int id) {
		this.itemId = id;
	}

	public int getWrapperId() {
        return wrapperId;
    }

    public void setWrapperId(int wrapperId) {
        this.wrapperId = wrapperId;
    }
   
    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getLastModifiedISO() {
		return lastModifiedISO;
	}

	public void setLastModifiedISO(String lastModifiedISO) {
		this.lastModifiedISO = lastModifiedISO;
	}

	public EPersonDTO getSubmitter() {
        return submitter;
    }

    public void setSubmitter(EPersonDTO submitter) {
        this.submitter = submitter;
    }

    public EPersonDTO getOwner() {
        return owner;
    }

    public void setOwner(EPersonDTO owner) {
        this.owner = owner;
    }

    public int getWorkflowState() {
        return workflowState;
    }

    public void setWorkflowState(int workflowState) {
        this.workflowState = workflowState;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }

    public int getWorkFlowValidationRule() {
        return workFlowValidationRule;
    }

    public void setWorkFlowValidationRule(int workFlowValidationRule) {
        this.workFlowValidationRule = workFlowValidationRule;
    }

    public boolean isReopened() {
        return reopened;
    }

    public void setReopened(boolean reopened) {
        this.reopened = reopened;
    }

    public int getWorkFlowValidationStatus() {
        return workFlowValidationStatus;
    }

    public void setWorkFlowValidationStatus(int workFlowValidationStatus) {
        this.workFlowValidationStatus = workFlowValidationStatus;
    }

    public int getIdentifierToDisseminate() {
        return identifierToDisseminate;
    }

    public void setIdentifierToDisseminate(int identifierToDisseminate) {
        this.identifierToDisseminate = identifierToDisseminate;
    }

    public int getInputFormId() {
        return inputFormId;
    }

    public void setInputFormId(int inputFormId) {
        this.inputFormId = inputFormId;
    }

    public Map<String, List<MetadataEntryRestDTO>> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, List<MetadataEntryRestDTO>> metadata) {
        this.metadata = metadata;
    }

    public List<BitstreamRestDTO> getBitstreams() {
        return bitstreams;
    }

    public void setBitstreams(List<BitstreamRestDTO> bitstreams) {
        this.bitstreams = bitstreams;
    }

    public List<EventLogRestDTO> getHistoryLog() {
        return historyLog;
    }

    public void setHistoryLog(List<EventLogRestDTO> historyLog) {
        this.historyLog = historyLog;
    }

    public Map<String, String> getLookupValues() {
        return lookupValues;
    }

    public void setLookupValues(Map<String, String> lookupValues) {
        this.lookupValues = lookupValues;
    }
    
    public List<InternalAuthorRestDTO> getInternalAuthors() {
    	return internalAuthors;
    }
    
    public void setInternalAuthors(List<InternalAuthorRestDTO> internalAuthors) {
    	this.internalAuthors = internalAuthors;
    }

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public String toString() {
		return "ItemRestDTO [collection=" + collection + ", collectionHandle=" + collectionHandle
				+ ", simpleItemStatus=" + simpleItemStatus + ", submitterID=" + submitterID + ", submitterNetID="
				+ submitterNetID + ", archived=" + archived + ", inWorkFlow=" + inWorkFlow + ", inWorkspace="
				+ inWorkspace + ", snapshot=" + snapshot + ", withdrawn=" + withdrawn + ", inarchive=" + inarchive
				+ ", earlyDraft=" + earlyDraft + ", itemId=" + itemId + ", identifier=" + identifier 
				+ ", wrapperId=" + wrapperId + ", lastModified=" + lastModified + ", lastModifiedISO=" + lastModifiedISO
				+ ", submitter=" + submitter + ", owner=" + owner + ", workflowState=" + workflowState + ", actions="
				+ actions + ", workFlowValidationRule=" + workFlowValidationRule + ", reopened=" + reopened
				+ ", workFlowValidationStatus=" + workFlowValidationStatus + ", identifierToDisseminate="
				+ identifierToDisseminate + ", inputFormId=" + inputFormId + ", metadata=" + metadata + ", bitstreams="
				+ bitstreams + ", historyLog=" + historyLog + ", lookupValues=" + lookupValues + ", internalAuthors="
				+ internalAuthors + "]";
	}
	
}
