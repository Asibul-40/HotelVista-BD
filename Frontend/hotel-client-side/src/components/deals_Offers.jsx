import React, { Fragment } from "react";

const DealOffers = () => {

    return (
        <Fragment>
            <section style={{ marginBottom: '20px' }}>
                <h2 style={{ marginBottom: '10px' }}>Deals and Offers</h2>
                <div style={{ display: 'flex', justifyContent: 'center', flexWrap: 'wrap' }}>
                <div style={{ width: '300px', margin: '10px', padding: '20px', borderRadius: '5px', boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)', background: '#fff', color: '#333' }}>
                    <h3 style={{ marginBottom: '10px' }}>Special Summer Discount</h3>
                    <p style={{ margin: '0px' }}>Enjoy up to 20% off on selected hotels. Limited time offer!</p>
                </div>
              <div style={{ width: '300px', margin: '10px', padding: '20px', borderRadius: '5px', boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)', background: '#fff', color: '#333' }}>
                <h3 style={{ marginBottom: '10px' }}>Last Minute Deals</h3>
                <p style={{ margin: '0px' }}>Grab amazing discounts on last-minute bookings. Don't miss out!</p>
              </div>
              {/* Add more deals and offers here */}
            </div>
          </section>
        </Fragment>
    )
}

export default DealOffers;