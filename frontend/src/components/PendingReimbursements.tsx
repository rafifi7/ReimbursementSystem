import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import Navbar from "./Navbar";
import { ReimbursementInterface } from '../interfaces/ReimbursementInterface';
import { Modal, Button } from "react-bootstrap"; // Importing Bootstrap components

const ListReimbursements: React.FC = () => {
  const [reimbursements, setReimbursements] = useState<ReimbursementInterface[]>([]);
  const [showModal, setShowModal] = useState(false);
  const [selectedReimbId, setSelectedReimbId] = useState<number | null>(null);
  const navigate = useNavigate();

  useEffect(() => {
    getAllReimbursements();
  }, []);

  const getAllReimbursements = async () => {
    try {
      const response = await axios.get("http://localhost:3001/reimbursements/pending", {
        withCredentials: true,
      });
      console.log(response.data);
      setReimbursements(response.data);
    } catch (error) {
      console.error("There was an error fetching the reimbursements!", error);
    }
  };

  const handleUpdateStatus = (reimbId: number) => {
    setSelectedReimbId(reimbId);
    setShowModal(true);
  };

  const handleClose = () => setShowModal(false);

  const handleAction = async (action: string) => {
    if (selectedReimbId) {
        try {
          await axios.post(
            `http://localhost:3001/reimbursements/updateStatus/${selectedReimbId}`,
            action, // Sending the string directly as the request body
            {
              withCredentials: true,
              headers: {
                'Content-Type': 'text/plain', // Set the Content-Type to plain text
              },
            }
          );
        console.log(`Reimbursement ID ${selectedReimbId} updated to ${action}`);
        getAllReimbursements(); // Refresh the list
        setShowModal(false);
      } catch (error) {
        console.error("There was an error updating the status!", error);
      }
    }
  };

  return (
    <div>
      <Navbar/>
      <div className="container mt-4">
        <h2 className="text-center">List of Pending Reimbursements</h2>
        <table className="table table-striped table-bordered">
          <thead>
            <tr>
              <th>Id</th>
              <th>Description</th>
              <th>Amount</th>
              <th>Status</th>
              <th>User Id</th>
              <th>Action</th> {/* New column for actions */}
            </tr>
          </thead>
          <tbody>
            {reimbursements.map((reimbursement) => (
              <tr key={reimbursement.reimbId}>
                <td>{reimbursement.reimbId}</td>
                <td>{reimbursement.description}</td>
                <td>{reimbursement.amount}</td>
                <td>{reimbursement.status}</td>
                <td>{reimbursement.user.userId}</td>
                <td>
                  <button
                    className="btn btn-primary"
                    onClick={() => handleUpdateStatus(reimbursement.reimbId!)}
                  >
                    Update Status
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* Modal for updating status */}
      <Modal show={showModal} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Update Reimbursement Status</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <p>Select an action for reimbursement with ID: {selectedReimbId}</p> {/* Display the ID here */}
          <div className="d-flex justify-content-between">
            <Button variant="success" onClick={() => handleAction("accepted")}>
              Accept
            </Button>
            <Button variant="danger" onClick={() => handleAction("denied")}>
              Deny
            </Button>
            <Button variant="primary" onClick={() => handleAction("pending")}>
              Pending
            </Button>
            <Button variant="secondary" onClick={handleClose}>
              Cancel
            </Button>
          </div>
        </Modal.Body>
      </Modal>
    </div>
  );
};

export default ListReimbursements;
