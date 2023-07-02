import React, { Fragment } from "react";

const FeaturedHotels = () => {
    return (
        <Fragment>
            <section style={{ marginBottom: '20px' }}>
                <h2 style={{ marginBottom: '20px' }}>Explore Nearby Attractions</h2>
                <div style={{ display: 'flex', justifyContent: 'center' }}>
                    <div style={{ width: '300px', margin: '10px', padding: '20px', borderRadius: '5px', boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)', background: '#fff', color: '#333' }}>
                        <h3 style={{ marginBottom: '10px' }}>Local Landmarks</h3>
                        <p style={{ margin: '0px' }}>Discover famous landmarks and cultural sites in the vicinity.</p>
                    </div>
                    <div style={{ width: '300px', margin: '10px', padding: '20px', borderRadius: '5px', boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)', background: '#fff', color: '#333' }}>
                        <h3 style={{ marginBottom: '10px' }}>Outdoor Activities</h3>
                        <p style={{ margin: '0px' }}>Experience thrilling outdoor adventures and recreational activities.</p>
                    </div>
    {/* Add more sections or cards for nearby attractions */}
                </div>
            </section>
        </Fragment>
    )
}

export default FeaturedHotels;